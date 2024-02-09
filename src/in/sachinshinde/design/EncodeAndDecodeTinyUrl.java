package in.sachinshinde.design;

import java.util.HashMap;
import java.util.Map;

//	https://leetcode.com/problems/encode-and-decode-tinyurl/

/*
 	TinyURL is a URL shortening service where you enter a URL such as 
 	https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk. 
 	Design a class to encode a URL and decode a tiny URL.

        There is no restriction on how your encode/decode algorithm should work. You just need to ensure that 
        a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
        
        Implement the Solution class:
        
        Solution() Initializes the object of the system.
        String encode(String longUrl) Returns a tiny URL for the given longUrl.
        String decode(String shortUrl) Returns the original long URL for the given shortUrl. 
        It is guaranteed that the given shortUrl was encoded by the same object.
         
        
        Example 1:
        
        Input: url = "https://leetcode.com/problems/design-tinyurl"
        Output: "https://leetcode.com/problems/design-tinyurl"
        
        Explanation:
        Solution obj = new Solution();
        string tiny = obj.encode(url); // returns the encoded tiny url.
        string ans = obj.decode(tiny); // returns the original url after decoding it.
         
        
        Constraints:
        
        1 <= url.length <= 104
        url is guranteed to be a valid URL.
 */

//	https://youtu.be/VyBOaboQLGc

public class EncodeAndDecodeTinyUrl {
    	Map<Integer, String> urlMap;
    
    	public String encode(String longUrl) {
		int id = 0;
		String BASE_URL = "http://tinyurl.com/";
				
		for (int i = longUrl.length() - 1; i >= 0; i--) {
		  if ('a' <= longUrl.charAt(i) &&
			longUrl.charAt(i) <= 'z')
			id = id * 62 + longUrl.charAt(i) - 'a';
		  if ('A' <= longUrl.charAt(i) &&
			longUrl.charAt(i) <= 'Z')
			id = id * 62 + longUrl.charAt(i) - 'A' + 26;
		  if ('0' <= longUrl.charAt(i) &&
			longUrl.charAt(i) <= '9')
			id = id * 62 + longUrl.charAt(i) - '0' + 52;
		}

		String encodedUrl = BASE_URL + Math.abs(id);
		urlMap.put(Math.abs(id), longUrl);
		return encodedUrl;
    	}	

    	// Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
        	String BASE_URL = "http://tinyurl.com/";
        	String id = shortUrl.substring(BASE_URL.length());
                Integer n = Integer.parseInt(id);
                return urlMap.get(n);
        }
    
        public static void main(String[] args) {
        	EncodeAndDecodeTinyUrl url = new EncodeAndDecodeTinyUrl();
        	url.urlMap = new HashMap<>();
        	String urlStr = "https://leetcode.com/problems/design-tinyurl";
        	String encodeUrl = url.encode(urlStr);
        	System.out.println(encodeUrl);
        	String decodeUrl = url.decode(encodeUrl);
        	System.out.println(decodeUrl);
        }
}
