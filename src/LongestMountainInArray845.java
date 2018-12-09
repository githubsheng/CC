/**
 * Created by sheng.wang on 2018/12/06.
 */
public class LongestMountainInArray845 {

    int longest = 0;

    public int longestMountain(int[] list) {

        if(list.length < 3) return 0;

        int longest = 0;
        int start = -1;
        int end = -1;
        int status = 1; //0 asc, 1 even, 2 desc

        for(int i = 0; i < list.length - 1; i++) {

            if(list[i] < list[i + 1]) {
                if(status == 1) {
                    start = i;
                } else if (status == 2) {
                    end = i;


                    if(start != -1) {
                        longest = Math.max(longest, end - start + 1);
                        if(longest == 6) {
                            System.out.println(1);
                        }
                    }

                    start = i;
                }
                status = 0;
            } else if (list[i] > list[i + 1]) {
                status = 2;
            } else if (list[i] == list[i + 1]) {
                if(status == 0) {
                    start = -1;
                } else if (status == 2) {
                    end = i;

                    if(start != -1) {
                        longest = Math.max(longest, end - start + 1);
                        if(longest == 6) {
                            System.out.println(1);
                        }
                    }

                    start = -1;

                }
                status = 1;
            }

        }

        if(status == 2) {
            end = list.length -1;
            if(start != -1) {
                longest = Math.max(longest, end - start + 1);
                if(longest == 6) {
                    System.out.println(1);
                }
            }
        }


        return longest;
    }

    public static void main(String args[]) {
        LongestMountainInArray845 sol = new LongestMountainInArray845();
        int[] input = new int[]{0,3,1,1,1,0,1,0};
        int ret = sol.longestMountain(input);
        System.out.println(ret);
    }

}
