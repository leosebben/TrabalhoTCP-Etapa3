package br.ufrgs.tcp.respondidos.model;

import java.util.ArrayList;
import java.util.List;

public enum Categoria {
    Historia, Matematica,Entretenimento, Esporte; // Geografia, Ciencia, Pop, Artes, Mix;


    public static Categoria getFromString(String str){
        switch( str.toLowerCase().trim() ){
            case "matematica": return Matematica;
            case "historia": return Historia;
            case "entretenimento": return Entretenimento;
            case "esporte": return Esporte;
            // case "geografia": return Geografia;
            // case "ciencia": return Ciencia;
            // case "pop": return Pop;
            // case "artes": return Artes;
            // case "mix":  return Mix;
        }

        throw new RuntimeException("Categoria não reconhecida! <" + str + ">");
    }

    public static List<Categoria> getAll(){
        ArrayList<Categoria> arr = new ArrayList<>();

        arr.add(Entretenimento);
        arr.add(Matematica);
        arr.add(Historia);
        arr.add(Esporte);
        // arr.add(Geografia);
        // arr.add(Ciencia);
        // arr.add(Pop);
        // arr.add(Artes);
        // arr.add(Mix);

        return arr;
    }
}
