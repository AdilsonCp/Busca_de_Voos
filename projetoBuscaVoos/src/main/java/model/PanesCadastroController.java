/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.scene.control.Label;

/**
 *
 * @author adils
 */
public abstract class PanesCadastroController {
    public abstract int validarCampos();
    public abstract void avisos();
    public abstract void configAvisos(Label labelAviso, String aviso, boolean  visible);

      
}
