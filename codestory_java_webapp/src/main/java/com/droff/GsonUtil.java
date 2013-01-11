package com.droff;

import com.google.gson.GsonBuilder;

public class GsonUtil
{
    private static GsonBuilder getGsonBuilder()
    {
        return new GsonBuilder().disableHtmlEscaping();
    }
    
    /**
     * @param objectToSerialize
     * @return a pretty printed json marshalled object with no html escaping 
     */
    public static String toJson(Object objectToSerialize)
    {
        return  getGsonBuilder().setPrettyPrinting().create().toJson(objectToSerialize);
    }
    
    /**
     * @param objectToSerialize
     * @param prettyPrint if true the json will be pretty-printed/human-readable
     * @return a json marshalled object with no html escaping 
     */
    public static String toJson(Object objectToSerialize, boolean prettyPrint)
    {
        
        if (prettyPrint)
           return toJson(objectToSerialize);
        else
           return getGsonBuilder().create().toJson(objectToSerialize);
    }
    
    
    public static <T> T fromJson(String json, Class<T> type)
    {
        return getGsonBuilder().create().fromJson(json, type);   
    }
    
    
   
}
