package edu.uga.cs.customlistview;

public class State {
    private String state;
    private String capital;

    public State() {
        this.state = null;
        this.capital = null;
    }

    public State(String state, String capital) {
        this.state = state;
        this.capital = capital;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "State{" +
                "state='" + state + '\'' +
                ", capital='" + capital + '\'' +
                '}';
    }
}
