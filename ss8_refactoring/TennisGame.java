package ss8_refactoring;

public class TennisGame {

    private static final int POINTS_TO_WIN = 4;
    private static final String[] SCORE_NAMES = {"Love", "Fifteen", "Thirty", "Forty"};

    private int player1Score;
    private int player2Score;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    private String getEqualScore(int score) {
        switch (score) {
            case 0: return "Love-All";
            case 1: return "Fifteen-All";
            case 2: return "Thirty-All";
            default: return "Deuce";
        }
    }
    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            player1Score++;
        else
            player2Score++;
    }

    private String getAdvantageOrWin() {
        int diff = player1Score - player2Score;
        if (diff == 1) return "Advantage " + player1Name;
        if (diff == -1) return "Advantage " + player2Name;
        if (diff >= 2) return "Win for " + player1Name;
        return "Win for " + player2Name;
    }

    public String getScore() {
        if (player1Score == player2Score)
            return getEqualScore(player1Score);
        if (player1Score >= POINTS_TO_WIN || player2Score >= POINTS_TO_WIN)
            return getAdvantageOrWin();
        return getNormalScore();
    }

    private String getNormalScore() {
        return SCORE_NAMES[player1Score] + "-" + SCORE_NAMES[player2Score];
    }



}
