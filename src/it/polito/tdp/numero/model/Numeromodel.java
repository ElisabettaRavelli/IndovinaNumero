package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;

import javafx.beans.property.IntegerProperty;

public class Numeromodel {

	private final int NMAX = 100;
	private final int TMAX = 8;

	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	//private IntegerProperty tentativiFatti;
	
	
	//COSTRUTTORE
	public void NumeroModel() {
		inGioco = false;
	}
	
	//INIZIA UNA NUOVA PARTITA
	public void newGame() {
		
		inGioco = true;
		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.tentativiFatti = 0;
	}
	
	//ANALISI DEL TENTATIVO
	public int tentativo(int t) {
		
		//controllare se la partita è in corso 
		if(!inGioco) {
			//lancio una eccezione e il metodo non prosegue
			throw new IllegalStateException("La partita è terminata");
		}
		
		//controllo se l'input è corretto
		if(!tentativoValido(t)) {
			throw new InvalidParameterException(String.format("Devi inserire un nuemro" +
							 "tra %d e %d" ,1, NMAX));
		}
		
		//GESTIONE TENTATIVO
		this.tentativiFatti++;
		
		if(this.tentativiFatti == this.TMAX){
			//la partita è finita perchè ho esaurito i tentativi
			this.inGioco = false; 
		}
		
		if(t==this.segreto) {
			//ho indovinato
			this.inGioco =false;
			return 0;
		}
		
		if(t> this.segreto) {
			return 1;
		}
		
		return -1;
	}
	
	//DICE SE IL TENTATIVO E' NEL RANGE GIUSTO OPPURE NO
	public boolean tentativoValido(int t) {
		if(t<1 || t>NMAX) {
			return false;
		} else return true;
		
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public boolean isInGioco() {
		return inGioco;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
}


