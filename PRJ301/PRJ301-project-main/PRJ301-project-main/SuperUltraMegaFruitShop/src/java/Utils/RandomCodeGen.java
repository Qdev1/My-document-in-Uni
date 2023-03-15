/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.util.Random;

/**
 *
 * @author Nezio
 */
public class RandomCodeGen {

    public RandomCodeGen() {
    }
    
    public String CodeGenerator(){
        String result;
        Random r = new Random();
        result= String.valueOf(r.nextInt(1000000));
        return result;
        
    }
}
