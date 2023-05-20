package model;

import java.io.Serializable;

public class Step implements Serializable, Comparable<model.Step> {
        private ChessboardPoint start;//当前棋子位置
        private ChessboardPoint end;//要行进到的位置
        private ChessPiece startChessPiece;//当前棋子
        private ChessPiece endChessPiece;//要行进到的位置的棋子
        private PlayerColor currentPlayer;
        private transient int value;
        private int turnCount;

        public Step(ChessboardPoint start, ChessboardPoint end, ChessPiece startChessPiece,
                    ChessPiece endChessPiece, PlayerColor currentPlayer, int turnCount) {
            this.start = start;
            this.end = end;
            this.startChessPiece = startChessPiece;
            this.endChessPiece = endChessPiece;
            this.currentPlayer = currentPlayer;
            this.turnCount = turnCount;
        }

        public ChessboardPoint getStart() {
            return start;
        }

        public ChessboardPoint getEnd() {
            return end;
        }

        public void setStart(ChessboardPoint start) {
            this.start = start;
        }

        public void setEnd(ChessboardPoint end) {
            this.end = end;
        }

        public ChessPiece getStartChessPiece() {
            return startChessPiece;
        }

        public ChessPiece getEndChessPiece() {
            return endChessPiece;
        }

        public void setStartChessPiece(ChessPiece startChessPiece) {
            this.startChessPiece = startChessPiece;
        }

        public void setEndChessPiece(ChessPiece endChessPiece) {
            this.endChessPiece = endChessPiece;
        }

        public PlayerColor getCurrentPlayer() {
            return currentPlayer;
        }

        public int getTurnCount() {
            return turnCount;
        }

        public void setCurrentPlayer(PlayerColor currentPlayer) {
            this.currentPlayer = currentPlayer;
        }

        public void setTurnCount(int turnCount) {
            this.turnCount = turnCount;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Step{" +
                    "from=" + start +
                    ", to=" + end +
                    ", fromChessPiece=" + startChessPiece +
                    ", toChessPiece=" + endChessPiece +
                    ", value=" + value +
                    '}';
        }

        @Override
        public int compareTo(model.Step o) {
            return o.getValue() - this.getValue();
        }
    }

