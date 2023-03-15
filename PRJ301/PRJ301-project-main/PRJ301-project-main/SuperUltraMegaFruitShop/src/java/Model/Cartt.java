/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author duonn
 */
@Builder
@Getter
@Setter
@ToString
public class Cartt {

    private Products product;
    private int quantity; //so luong sp trong gio hang

}
