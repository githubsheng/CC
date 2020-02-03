package references.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Commons {

public final int characterOffset = 97;

    //ignore...
    int[] aaaa = new int[5];
    int[] bbbb = new int[5];
    List<String> cccc = new ArrayList<>();


    public void forLoops() {

        for(int i = 0; i < aaaa.length; i++) {

            //array

        }

        for(int i = 0; i < cccc.size(); i++) {

            //list

        }


        for(int i = 0; i < aaaa.length; i++) {

            for(int j = 0; j < bbbb.length; j++) {

                //nested array

            }

        }

        for(int i = 0; i < cccc.size(); i++) {


            for(int j = 0; j < cccc.size(); j--) {

                //nested list

            }

        }


    }

}
