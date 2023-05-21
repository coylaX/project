package computerplayer;

import model.Chessboard;
import model.PlayerColor;
import model.Step;

import java.util.Collections;
import java.util.List;

public class AIPlayer extends Thread {
    private GameMode gameMode;
    private Chessboard model;

    public AIPlayer(GameMode gameMode, Chessboard model) {
        this.gameMode = gameMode;
        this.model = model;
    }

    public Step generateMove(PlayerColor color, int turn) {
        // 生成一个合适的AI走棋步骤
        // 简化起见，这里仅选择一个合法的随机移动
        if (gameMode == GameMode.Random) {
            return AIMoveMode1(color, turn);
        } else if (gameMode == GameMode.Greedy) {
            return AIMoveMode2(color, turn);
        }
        return null;
    }

    // 随机
    private Step AIMoveMode1(PlayerColor color,int turn) {
        List<Step> steps = model.getValidSteps(color, turn);
        Step b = null;
        if (steps.size() > 0) {
            b = steps.get((int) (Math.random() * steps.size()-1));
        }
        return b;
    }


    // 贪心
    private Step AIMoveMode2(PlayerColor color, int turn) {
        List<Step> steps = model.allLegalStepsIncludeValue(color);
        Step re = null;
        //从大到小排序
        Collections.sort(steps);
        //打印value
        int max = steps.get(0).getValue();
        int n = 0;
        for (Step step : steps) {
            System.out.print(step.getValue() + " ");
            if(step.getValue()==max)
            n++;
        }
        System.out.println();
        re = steps.get((int) (Math.random() * n));
        return re;
    }


}

