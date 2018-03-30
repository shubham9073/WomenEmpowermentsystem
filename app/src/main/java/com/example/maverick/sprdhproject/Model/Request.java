package com.example.maverick.sprdhproject.Model;

import java.util.ArrayList;

/**
 * Created by anant bansal on 6/4/2017.
 */

public class Request {
    ArrayList<Result> results;

    public Request(ArrayList<Result> results) {
        this.results = results;
    }

    public ArrayList<Result> getResults() {
        return results;
    }
}
