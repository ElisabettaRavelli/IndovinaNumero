package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Numeromodel {
	
	//private List<Integer> tentativi;
	private Set<Integer> tentativi;
	private final int NMAX = 100;
	private final int TMAX = 8;

	private int segreto;
	//private int tentativiFatti;
	private boolean inGioco = false;
	//UTILIZZO DELLE PROPERTY PER PASSARE DA MODELLO A VISTA SENZA PASSARE DAL CONTROLLER
	private IntegerProperty tentativiFatti;
	
	
	//COSTRUTTORE
	public void NumeroModel() {
		inGioco = false;
		//è necessario fare una new perchè tentativiFatti è diventato una Property e quindi un oggetto
		tentativiFatti = new SimpleIntegerProperty();
		//tentativi = new LinkedList<Integer>(); 
		tentativi = new HashSet<Integer>();
	}
	
	//INIZIA UNA NUOVA PARTITA
	public void newGame() {
		
		inGioco = true;
		this.segreto = (int) (Math.random() * NMAX) + 1;
		//this.tentativiFatti=0;
		this.tentativiFatti.set(0);
		//this.tentativi = new LinkedList<Integer>();
		tentativi = new HashSet<Integer>();
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
		//this.tentativiFatti++;
		this.tentativiFatti.set(this.tentativiFatti.get()+1);
		//this.tentativi.add(t);
		this.tentativi.add(new Integer(t));
		
		//if(this.tentativiFatti == this.TMAX){
		if(this.tentativiFatti.get() == this.TMAX){
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
		} else {
			if(this.tentativi.contains(t))
			//if(this.tentativi.contains(new Integer(t)))
				return false;
			else return true;
		}
		
	}

	public int getSegreto() {
		return segreto;
	}

	/*public int getTentativiFatti() {
		return tentativiFatti;
	}*/

	public boolean isInGioco() {
		return inGioco;
	} 

	public int getTMAX() {
		return TMAX;
	}
	
	public int getNMAX() {
		return NMAX;
	}

	public final IntegerProperty tentativiFattiProperty() {
		return this.tentativiFatti;
	}
	
	public final int getTentativiFatti() {
		return this.tentativiFattiProperty().get();
	}
	
	public final void setTentativiFatti(final int tentativiFatti) {
		this.tentativiFattiProperty().set(tentativiFatti);
	}
}


