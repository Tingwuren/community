package cn.edu.bupt.community.util;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class SensitiveFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    private static final String REPLACMENT = "***";

    private TrieNode rootNode = new TrieNode();

    @PostConstruct
    public void init() {
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
            if (is == null) {
                throw new FileNotFoundException("在类路径中未找到资源文件 'sensitive-words.txt'");
            }
            reader = new BufferedReader(new InputStreamReader(is));
            String keyword;
            while ((keyword = reader.readLine()) != null) {
                this.addKeyword(keyword);
            }
        } catch (IOException e) {
            logger.error("读取敏感词文件时发生错误: " + e.getMessage(), e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                logger.error("关闭资源时发生错误: " + e.getMessage(), e);
            }
        }
    }

    private void addKeyword(String keyword) {
        TrieNode tempNode = rootNode;
        for (int i = 0; i < keyword.length(); i ++) {
            char c = keyword.charAt(i);
            TrieNode subNode = tempNode.getSubNode(c);

            if (subNode == null) {
                subNode = new TrieNode();
                tempNode.addSubNode(c, subNode);
            }

            tempNode = subNode;

            if (i == keyword.length() - 1) {
                tempNode.setKeywordEnd(true);
            }
        }
    }

    public String filter(String text) {

        if (StringUtils.isBlank(text)) {
            return null;
        }

        TrieNode tempNode = rootNode;
        int begin = 0;
        int position = 0;
        StringBuilder sb = new StringBuilder();

        while (position < text.length()) {
            char c = text.charAt(position);

            if (isSymbol(c)) {
                if (tempNode == rootNode) {
                    sb.append(c);
                    begin++;
                }
                position++;
                continue;
            }

            tempNode = tempNode.getSubNode(c);
            if (tempNode == null) {
                sb.append(text.charAt(begin));
                begin++;
                position = begin;
                tempNode = rootNode;
            } else if (tempNode.isKeywordEnd()) {
                sb.append(REPLACMENT);
                position++;
                begin = position;
                tempNode = rootNode;
            } else {
                position++;
            }
        }

        sb.append(text.substring(begin));

        return sb.toString();
    }

    private boolean isSymbol(Character c) {
        if (CharUtils.isAsciiAlphanumeric(c)) {
            return false;
        }
        if (c >= 0x2E80 && c < 0x9FFF) {
            return false;
        }
        return true;
    }

    private class TrieNode {

        private boolean isKeywordEnd = false;

        private Map<Character, TrieNode> subNodes = new HashMap<>();

        public boolean isKeywordEnd() {
            return isKeywordEnd;
        }

        public void setKeywordEnd(boolean keywordEnd) {
            isKeywordEnd = keywordEnd;
        }

        public void addSubNode(Character c, TrieNode node) {
            subNodes.put(c, node);
        }

        public TrieNode getSubNode(Character c) {
            return subNodes.get(c);
        }

    }
}
