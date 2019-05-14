/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banner.entity;

import java.io.Serializable;

/**
 *
 * @author eKreasi Chubb Team ( Fairuz Fatin, Bayu Sugara, Maretta, Muhamad Zamil, Fhad Saleh )
 */
public class BannerPK implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
