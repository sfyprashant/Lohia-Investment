package com.Lohia.investment.Models;

public class User_profile_model {
    String name, health,car,life,health_cover,life_cover;

    public User_profile_model(String name, String health, String car, String life,int health_cover,int life_cover) {
        this.name = name;
        this.health = health;
        this.car = car;
        this.life = life;
        this.life_cover = String.valueOf(life_cover);
        this.health_cover = String.valueOf(health_cover);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getLife_cover() {
        return life_cover;
    }

    public void setLife_cover(String life_cover) {
        this.life_cover = life_cover;
    }

    public String getHealth_cover() {
        return health_cover;
    }

    public void setHealth_cover(String health_cover) {
        this.health_cover = health_cover;
    }
}
