// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 04-09-2015 14:59:51
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PDFDictionary.java

package cl.araucana.fpg;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package cl.araucana.fpg:
//            FPGException

public class PDFDictionary
{

    public PDFDictionary()
    {
        words = new ArrayList();
        values = new ArrayList();
    }

    public PDFDictionary(String text)
        throws FPGException
    {
        words = new ArrayList();
        values = new ArrayList();
        int beginIndex = text.indexOf("<<");
        int endIndex = text.lastIndexOf(">>");
        if(beginIndex == -1 || endIndex == -1)
            throwFPGException(text);
        text = text.substring(beginIndex + 2, endIndex + 2);
        int i = 0;
        int j = text.length();
        String word = null;
        while(i < j) 
        {
            char ch = text.charAt(i);
            if(ch == '/')
            {
                String token;
                for(token = "/"; ++i < j; token = token + ch)
                {
                    ch = text.charAt(i);
                    if(!isValidWordCharacter(ch))
                        break;
                }

                if(word == null)
                {
                    word = token;
                } else
                {
                    addWord(word, token);
                    word = null;
                }
            } else
            if(" \t\r\n".indexOf(ch) >= 0)
                i++;
            else
            if(ch == '[')
            {
                if(word == null)
                    throwFPGException(text);
                String token;
                for(token = "["; ++i < j && ch != ']'; token = token + ch)
                    ch = text.charAt(i);

                addWord(word, token);
                word = null;
            } else
            if(ch == '(')
            {
                if(word == null)
                    throwFPGException(text);
                boolean escaped = false;
                String token = "(";
                while(++i < j) 
                {
                    ch = text.charAt(i);
                    if(escaped)
                    {
                        token = token + ch;
                        escaped = false;
                        continue;
                    }
                    if(ch == '\\')
                    {
                        escaped = true;
                        continue;
                    }
                    token = token + ch;
                    if(ch != ')')
                        continue;
                    i++;
                    break;
                }
                addWord(word, token);
                word = null;
            } else
            {
                int k = i;
                String token;
                for(token = (new StringBuffer(String.valueOf(ch))).toString(); ++i < j; token = token + ch)
                {
                    ch = text.charAt(i);
                    if("/\t\r\n".indexOf(ch) >= 0)
                        break;
                }

                int index = token.indexOf(">>");
                if(index >= 0)
                {
                    if(word != null)
                        addWord(word, token.substring(0, index));
                    else
                    if(index > 0)
                        throwFPGException(text);
                    this.index = k + index + 2;
                    return;
                }
                if(word == null)
                    throwFPGException(text);
                index = token.indexOf("<<");
                if(index >= 0)
                {
                    if(index > 0)
                        throwFPGException(text);
                    String subText = text.substring(k);
                    PDFDictionary subDict = new PDFDictionary(subText);
                    addWord(word, subDict);
                    i = k + 2 + subDict.index;
                } else
                {
                    addWord(word, token);
                }
                word = null;
            }
        }
        if(word != null)
            throwFPGException(text);
    }

    public void addWord(String word, Object value)
        throws FPGException
    {
        checkWord(word);
        if(words.contains(word))
            throw new FPGException("Duplicated word '" + word + "'");
        if(value == null)
            throw new FPGException("Word value cannot be null");
        if(value instanceof String)
        {
            value = ((String)value).trim();
            if(value.equals(""))
                throw new FPGException("Word value cannot be empty");
        }
        words.add(word);
        values.add(value);
    }

    public void removeWord(String word)
    {
        int index = words.indexOf(word);
        if(index >= 0)
        {
            words.remove(index);
            values.remove(index);
        }
    }

    public int wordCount()
    {
        return words.size();
    }

    public String getWord(int index)
    {
        if(index < 0 || index >= words.size())
            throw new IndexOutOfBoundsException((new StringBuffer(String.valueOf(index))).toString());
        else
            return (String)words.get(index);
    }

    public int getWordIndex(String word)
    {
        return words.indexOf(word);
    }

    public Object getObjectValue(String word)
    {
        int index = words.indexOf(word);
        if(index == -1)
            return null;
        else
            return values.get(index);
    }

    public String getValue(String word)
    {
        int index = words.indexOf(word);
        if(index == -1)
            return null;
        else
            return (String)values.get(index);
    }

    public PDFDictionary getPDFDictionary(String word)
    {
        int index = words.indexOf(word);
        if(index == -1)
            return null;
        else
            return (PDFDictionary)values.get(index);
    }

    public int getIntValue(String word)
        throws NumberFormatException
    {
        String value = getValue(word);
        if(value == null)
            throw new IllegalArgumentException("Unknown word '" + word + "'");
        else
            return Integer.parseInt(value);
    }

    public int getObjIDRefValue(String word)
        throws NumberFormatException
    {
        String value = getValue(word);
        if(value == null)
            throw new IllegalArgumentException("Unknown word '" + word + "'");
        String tokens[] = value.split(" ");
        if(tokens.length != 3 || !tokens[1].equals("0") || !tokens[2].equals("R"))
            throw new NumberFormatException("Invalid objID reference '" + value + "'");
        else
            return Integer.parseInt(tokens[0]);
    }

    public String toString()
    {
        String s = "dict{";
        for(int i = 0; i < words.size(); i++)
        {
            s = s + words.get(i) + "=" + values.get(i);
            if(i + 1 < words.size())
                s = s + " ";
        }

        return s + "}";
    }

    public byte[] assemble()
    {
        return assemble(true, true);
    }

    public byte[] assemble(boolean opened, boolean closed)
    {
        String aux;
        if(opened)
            aux = "<<\n";
        else
            aux = "";
        for(int i = 0; i < words.size(); i++)
        {
            Object value = values.get(i);
            aux = aux + words.get(i) + " ";
            if(value instanceof PDFDictionary)
            {
                PDFDictionary dict = (PDFDictionary)value;
                aux = aux + new String(dict.assemble());
            } else
            {
                aux = aux + value + "\n";
            }
        }

        if(closed)
            aux = aux + ">>\n";
        return aux.getBytes();
    }

    private void checkWord(String word)
        throws FPGException
    {
        boolean ok = true;
        if(word == null || !word.startsWith("/"))
        {
            ok = false;
        } else
        {
            for(int i = 1; i < word.length(); i++)
            {
                char ch = word.charAt(i);
                if(isValidWordCharacter(ch))
                    continue;
                ok = false;
                break;
            }

        }
        if(!ok)
            throw new FPGException("Invalid word '" + word + "'");
        else
            return;
    }

    private boolean isValidWordCharacter(char ch)
    {
        return Character.isLetterOrDigit(ch) || "-+".indexOf(ch) >= 0;
    }

    private void throwFPGException(String text)
        throws FPGException
    {
        throw new FPGException("Unexpected PDF dictionary '" + text + "'");
    }

    private static final String WHITE_SPACES = " \t\r\n";
    private static final String DICT_WORD_SEPARATORS = "/\t\r\n";
    private static final String DICT_WORD_CHARACTERS = "-+";
    private List words;
    private List values;
    private int index;
}
