package com.sample.basics.Pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity  // This annotation allows the class to be embedded in another entity
public class Cast {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@NotEmpty(message = "Hero name cannot be empty")
    private String heroName;
	
	@NotEmpty(message = "Heroine name cannot be empty")
    private String heroineName;
	
	@NotEmpty(message = "Villain name cannot be empty")
    private String villain;
    

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVillain() {
		return villain;
	}

	public void setVillain(String villain) {
		this.villain = villain;
	}

	// Getters and Setters
    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroineName() {
        return heroineName;
    }

    public void setHeroineName(String heroineName) {
        this.heroineName = heroineName;
    }

   
}

