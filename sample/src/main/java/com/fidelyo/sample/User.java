package com.fidelyo.sample;

import com.fidelyo.widgets.Chip;

/**
 * Created by bishoy on 2/23/18.
 */

public class User implements Chip {

    private String name;
    private Integer index;

    @Override
    public Integer index() {
        return index;
    }

    @Override
    public void index(Integer index) {
        this.index = index;
    }

    @Override
    public String text() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
