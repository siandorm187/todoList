import controller.TaskController;
import model.Task;
import view.TaskView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static Task getTaskFromDB(){
        Task task = new Task();
        task.setId(1);
        task.setName("jmeno");
        task.setDescription("popis");
        task.setState(2);
        task.setDate("2023-08-30 11:05:00");
        return task;
    }

    public static void main(String[] args) {
        Task task = getTaskFromDB();
        TaskView view = new TaskView();
        TaskController controller = new TaskController(task, view);

        controller.updateView();
    }

    /*public int solution(int N){
        String binary = Integer.toBinaryString(N);
        char[] chBin = binary.toCharArray();

        ArrayList<Integer> gaps = new ArrayList<>();

        int result = 0;

        int i = 0;
        int j = 0;
        for(char chA : chBin){
            if(chA == 1 && i == 0){
                i = 1;
            }
            else{
                if(chA == 1){
                    i = 0;
                    gaps.add(j);
                }
                else{
                    if(chA == 0){
                        j++;
                    }
                }
            }
        }
        if(i == 1){
            result = 0;
        }
        else{
            int max = 0;

            int iaid = 0;
            for(int itema : gaps){
                int ibid = 0;
                for(int itemb : gaps){
                    if(iaid != ibid){
                        if(itema > itemb && itema > max){
                            max = itema;
                        }
                    }
                }
                iaid++;
            }

            result = max;
        }


        return result;
    }

    public int solutionTennisCourts(int P, int C){
        int games = (P-P%2)/2;

        int result = 0;
        if(games >= C){
            result = C;
        }
        else{
            result = games;
        }

        return result;
    }

    public int solutionNMums(int[] A){
        int result = -1;
        int indexCA = 0;

        int uniId = -1;

        for(int ca : A){
            int indexCB = 0;
            int sameC = 0;
            for(int cb : A){
                if(indexCA == indexCB){
                    continue;
                }
                else{


                    if(ca == cb){
                        sameC++;
                    }
                    int id = uniId;
                    if(sameC == 0){

                        if(uniId == -1){
                            uniId = indexCA;
                        }
                        else{
                            if(uniId > indexCA){
                                uniId = indexCA;
                            }
                        }
                    }


                }
                indexCB++;
            }
            indexCA++;
        }
        result = uniId;

        return result;
    }*/
}