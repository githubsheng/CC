package indeed;

public class ReverseHtml {

    /*
    http://www.1point3acres.com/bbs/thread-158403-1-1.html
    reverse string except HTML entity.1point3acres缃�
    eg.  // "1234&euro;" => &euro;4321"
         // "1234&euro" => "orue&4321"
         // "1234&euro;567" => "765&euro;4321". 1poi
     */
    public String reverseHtmlSheng(String html) {
        char[] arr = new StringBuilder(html).reverse().toString().toCharArray();
        int i = 0;
        while(i < arr.length) {
            char c = arr[i];
            if(c == ';') i = tryRecreateEntity(arr, i); //processed chars until i (inclusive)
            i++;
        }
        return new String(arr);
    }

    //try to process the arr from index start, and tries to recreate a html entity
    //it may or may not recreate an html entity, but either way, it returns the index
    //of the last character it processed (index i). caller should resume from the next
    //character from i.
    private int tryRecreateEntity(char[] arr, int start) {
        int i = start;
        while(i < arr.length - 1) {
            char nextChar = arr[i + 1];
            if(nextChar == '&') {
                reverseArray(arr, start, i + 1);
                return i + 1; //include & as processed
            } else if (nextChar == ';') {
                return i; //process all chars before next ';'
            } else {
                i++;
            }
        }
        return i; //index of last char processed
    }

    //reserve a part of array.
    private void reverseArray(char[] arr, int start, int end) {
        while(start < end) {
            char tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start++;
            end--;
        }
    }
    public static void main(String[] args) {
        ReverseHtml sol = new ReverseHtml();
        String s = "1234&eur;o;5677&&eu;567&"; // expected: &765&eu;&7765;o&eur;4321
        System.out.println(sol.reverseHtmlSheng(s));
        String s2 = "1234&euro;567";  //expected: "765&euro;4321"
        System.out.println(sol.reverseHtmlSheng(s2));
    }

// below are not so good quality code from others.
//    public  String  reverseHtml(String html) {
//        if(html == null || html.length()==0) {
//            return null;
//        }
//        int  len = html.length();
//        char[] chArr = html.toCharArray();
//        reverseChar(chArr,0,len-1);
//        int start = 0;
//        int  end = 0;
//        while(end<len){
//            if(chArr[end] == ';'){
//                start = end;
//            }else if(chArr[end] == '&'){
//                if(chArr[start] == ';')
//                    reverseChar(chArr,start,end);
//                start = end+1;
//            }
//            end++;
//
//        }
//        return new String(chArr);
//    }

//    public String reverseHtml2(String html) {
//        if(html == null || html.length() < 2){
//            return html;
//        }
//        int len = html.length();
//        char[] htmlArr = html.toCharArray();
//        reverseChar(htmlArr,0,len -1);
//        int left = 0;
//        while(left < len){
//            if(htmlArr[left] != ';'){
//                left++;
//            }else {
//                int right = left+1;
//                if(right >= len -1){
//                    break;
//                }
//                while(htmlArr[right] != '&'){
//                    if(htmlArr[right] == ';'){
//                        left = right;
//                    }
//                    right++;
//                }
//                reverseChar(htmlArr,left,right);
//                left = right+1;
//            }
//        }
//        return new String(htmlArr);
//    }
//    private  void reverseChar(char[] chars,int start,int end){
//        while(start < end) {
//            char temp = chars[start];
//            chars[start++] = chars[end];
//            chars[end--] = temp;
//        }
//    }

}
