package com.init.pokemon.rest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.pokemon.entitys.Pokemon;

@RestController
@RequestMapping("pokemon")
public class PokemonREST {
	
	public static final String SEPARATOR=",";
	public static final String QUOTE="\"";
	
	
	
	@RequestMapping(value="listpokemon", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Pokemon>> getPokemones() throws IOException{

	      BufferedReader br = null;
	      
	      ArrayList<Pokemon> pokemones = new ArrayList<Pokemon>();
	      
	      try {
	         
	         br =new BufferedReader(new FileReader("csvpokemon/pokemon.csv"));
	         String line = br.readLine();
	         
	         int bandera = 0;
	         
	         while (null!=line) {
	        	
	        	if(bandera == 0) {
	        		bandera = 1;
	        		line = br.readLine();
	        	}
	        	else {
	        		
	        		Pokemon pokemon = new Pokemon();
		        	
		            String [] fields = line.split(SEPARATOR);
		            
		            pokemon.setCodigo( Integer.parseInt(fields[0]) );
		    		pokemon.setNombre( fields[1].toString() );
		    		pokemon.setType1( fields[2].toString() );
		    		pokemon.setType2( fields[3].toString() );
		    		pokemon.setTotal( Integer.parseInt( fields[4] ) );
		    		pokemon.setHp( Integer.parseInt( fields[5] ) );
		    		pokemon.setAttack( Integer.parseInt( fields[6] ) );
		    		pokemon.setDefense( Integer.parseInt( fields[7] ) );
		    		pokemon.setSpattack( Integer.parseInt( fields[8] ) );
		    		pokemon.setSpdefense( Integer.parseInt( fields[9] ) );
		    		pokemon.setSpeed( Integer.parseInt( fields[10] ) );
		    		pokemon.setGeneration( Integer.parseInt( fields[11] ) );
		    		pokemon.setLegendary( Boolean.parseBoolean( fields[12] ) );
		    		
		    		pokemones.add(pokemon);
		            
		            //System.out.println(Arrays.toString(fields));	            
		            
		            
		            line = br.readLine();
		         }
	        	}
	        		        	
	         
	      } catch (Exception e) {
	    	  System.out.println(e);
	      } finally {
	         if (null!=br) {
	            br.close();
	         }
	      }
	      
	      return ResponseEntity.ok(pokemones);
	      
	}
	
	//@GetMapping
	@RequestMapping(value="pokemon", method = RequestMethod.GET)
	public ResponseEntity<Pokemon> getPokemon( @RequestBody Pokemon pokemon ){
		
		
		BufferedReader br = null;
		Pokemon pokemonfind = new Pokemon();
	      
	      try {
	         
	         br =new BufferedReader(new FileReader("csvpokemon/pokemon.csv"));
	         String line = br.readLine();
	         
	         int bandera = 0;
	         
	         while (null!=line) {
	        	
	        	if(bandera == 0) {
	        		bandera = 1;
	        		line = br.readLine();
	        	}
	        	else {
		        	
		            String [] fields = line.split(SEPARATOR);
		            
		            pokemonfind.setCodigo( Integer.parseInt(fields[0]) );
		            pokemonfind.setNombre( fields[1].toString() );
		            
		            if( (pokemon.getCodigo() == pokemonfind.getCodigo()) && (pokemon.getNombre().equalsIgnoreCase(pokemonfind.getNombre() ) ) ){
		            	
		            	pokemonfind.setType1( fields[2].toString() );
			            pokemonfind.setType2( fields[3].toString() );
			            pokemonfind.setTotal( Integer.parseInt( fields[4] ) );
			            pokemonfind.setHp( Integer.parseInt( fields[5] ) );
			            pokemonfind.setAttack( Integer.parseInt( fields[6] ) );
			            pokemonfind.setDefense( Integer.parseInt( fields[7] ) );
			            pokemonfind.setSpattack( Integer.parseInt( fields[8] ) );
			            pokemonfind.setSpdefense( Integer.parseInt( fields[9] ) );
			            pokemonfind.setSpeed( Integer.parseInt( fields[10] ) );
			            pokemonfind.setGeneration( Integer.parseInt( fields[11] ) );
			            pokemonfind.setLegendary( Boolean.parseBoolean( fields[12] ) );
		            	
			            break;
		            }else {
		            	
		            	pokemonfind.setCodigo( 0 );
			            pokemonfind.setNombre( "" );
		            }
		            
		            line = br.readLine();
		         }
	        	}
	         
	         br.close();
	        		        	
	         
	      } catch (Exception e) {
	    	  System.out.println(e);
	      } 
		
		
		return ResponseEntity.ok(pokemonfind);
	}
	
	@RequestMapping(value="newpokemon", method = RequestMethod.POST)
	public ResponseEntity<Pokemon> newPokemon(@RequestBody Pokemon pokemon){
		
		try {
			
			FileWriter pw = new FileWriter("csvpokemon/pokemon.csv", true);
	        
	        if (pokemon == null){
	            System.out.println("Empty");
	        }
	        else{
	            
	        	String line = String.valueOf( pokemon.getCodigo() ) + ',' + pokemon.getNombre() + ',' + 
	        			pokemon.getType1() + ',' + pokemon.getType2() + ',' + pokemon.getTotal() + ',' + 
	        			pokemon.getHp() + ',' + pokemon.getAttack() + ',' + pokemon.getDefense() + ',' +
	        			pokemon.getSpattack() + ',' + pokemon.getSpdefense() + ',' + pokemon.getSpeed() + ',' +
	        			pokemon.getGeneration() + ',' + pokemon.isLegendary() + '\n';
	        	
	        	pw.write(line); 
	        	
	        }
	            pw.flush();
	            pw.close();
	            
	            			
			
		}catch (Exception e) {
			System.out.println(e);
		}		
		
		return ResponseEntity.ok(pokemon);
	}
	
	
	@RequestMapping(value="deletePokemon", method = RequestMethod.DELETE)
	public ResponseEntity<Pokemon> deletePokemon( @RequestBody Pokemon pokemon ){
		
		BufferedReader br = null;
	      
	      try {
	    	  
	    	  br =new BufferedReader(new FileReader("csvpokemon/pokemon.csv"));
		      String line = br.readLine();
		      
		      File filetmp = new File("csvpokemon/pokemontmp.csv");
	          FileOutputStream fos = new FileOutputStream(filetmp);
	          OutputStreamWriter osw = new OutputStreamWriter(fos);
	          Writer w = new BufferedWriter(osw);
		         
		      int bandera = 0; //Identifica la primera linea
		      int bandera1 = 0; //Identifica si se encontro registro para eliminar
		         
	          while (null!=line) {
	        	
	        	 if(bandera == 0) {
	        	  	bandera = 1;
	        	 	line = br.readLine();
	        	 }
	        	 else {
	        		
	        	 	 Pokemon pokemonfile = new Pokemon();
		        	
		             String [] fields = line.split(SEPARATOR);
		            
		             pokemonfile.setCodigo( Integer.parseInt(fields[0]) );
		             pokemonfile.setNombre( fields[1].toString() );
		            
		             if( !(pokemon.getCodigo() == pokemonfile.getCodigo()) && !(pokemon.getNombre().equalsIgnoreCase(pokemonfile.getNombre() ) ) ) {
		            	
		                String registry = fields[0].toString() + ',' + fields[1].toString() + ',' + 
		            			fields[2].toString() + ',' + fields[3].toString() + ',' + fields[4].toString() + ',' + 
		            			fields[5].toString() + ',' + fields[6].toString() + ',' + fields[7].toString() + ',' +
		            			fields[8].toString() + ',' + fields[9].toString() + ',' + fields[10].toString() + ',' +
		            			fields[11].toString() + ',' + fields[12].toString() + '\n';
		            	
		            	w.write(registry);			            	
		             }
		             else {
		            	 bandera1 = 1;
		             }
		            
		             line = br.readLine();
		         }
	        	}
	          
	          br.close();
	          w.close();
	    	  
	    	  if( bandera1 == 1 ) {
	    		
	    		//Delete old file pokemon.csv
		          File fichero = new File("csvpokemon/pokemon.csv");
		          
		          FileInputStream readFile = new FileInputStream(fichero);
		          readFile.close(); //garantizar que el archivo este cerrado
		          
		          if (fichero.delete())
		              System.out.println("El fichero ha sido borrado satisfactoriamente");
		          else {
		        	
		        	  System.out.println("El fichero no pudó ser borrado");
		        	  File ficherotmp = new File("csvpokemon/pokemontmp.csv");

		        	  ficherotmp.delete();		        	  
		          }		    	  
		         
		    	  //Rename file pokemontmp.csv
		          File f1 = new File("csvpokemon/pokemontmp.csv");
		          File f2 = new File("csvpokemon/pokemon.csv");
		          
		          if ( f1.renameTo(f2) )
		              System.out.println("El fichero ha sido renombrado satisfactoriamente");
		          else
		              System.out.println("El fichero no pudó ser renombrado");	    		  
	    	  }
	    	  else {
	    		  
	    		  File fichero = new File("csvpokemon/pokemontmp.csv");

		          if (fichero.delete())
		              System.out.println("El fichero ha sido borrado satisfactoriamente");
		          else
		              System.out.println("El fichero no pudó ser borrado");
	    	  }
	    	  
	         
	      } catch (Exception e) {
	    	  System.out.println(e);
	      } 
		
		
		return ResponseEntity.ok(pokemon);
	}
	
	@RequestMapping(value="updatePokemon", method = RequestMethod.PUT)
	public ResponseEntity<Pokemon> updatePokemon( @RequestBody Pokemon pokemon ){
		
		BufferedReader br = null;
	      
	      try {
	    	  
	    	  br =new BufferedReader(new FileReader("csvpokemon/pokemon.csv"));
		      String line = br.readLine();
		      
		      File filetmp = new File("csvpokemon/pokemontmp.csv");
	          FileOutputStream fos = new FileOutputStream(filetmp);
	          OutputStreamWriter osw = new OutputStreamWriter(fos);
	          Writer w = new BufferedWriter(osw);
		         
		      int bandera = 0; //Identifica la primera linea
		      int bandera1 = 0; //Identifica si se encontro registro para eliminar
		      int bandera2 = 0; //Identifica si existe el registro a actualizar
		      
		      
		      //Identificar si existe el registro a actualizar
		      while (null!=line) {
		        	
		        	 if(bandera == 0) {
		        	  	bandera = 1;
		        	 	line = br.readLine();
		        	 }
		        	 else {
		        		
		        	 	 Pokemon pokemonfile = new Pokemon();
			        	
			             String [] fields = line.split(SEPARATOR);
			            
			             pokemonfile.setCodigo( Integer.parseInt(fields[0]) );
			             pokemonfile.setNombre( fields[1].toString() );
			            
			             if( (pokemon.getCodigo() == pokemonfile.getCodigo()) && (pokemon.getNombre().equalsIgnoreCase(pokemonfile.getNombre() ) ) ) {
			            	
			            	 bandera2 = 1;
			             }
		        	 }
		        	 
		        	 line = br.readLine();
		      }
			            	
		     if(bandera2 == 1) {
		      
	          while (null!=line) {
	        	
	        	 if(bandera == 0) {
	        	  	bandera = 1;
	        	 	line = br.readLine();
	        	 }
	        	 else {
	        		
	        	 	 Pokemon pokemonfile = new Pokemon();
		        	
		             String [] fields = line.split(SEPARATOR);
		            
		             pokemonfile.setCodigo( Integer.parseInt(fields[0]) );
		             pokemonfile.setNombre( fields[1].toString() );
		            
		             if( !(pokemon.getCodigo() == pokemonfile.getCodigo()) && !(pokemon.getNombre().equalsIgnoreCase(pokemonfile.getNombre() ) ) ) {
		            	
		                String registry = fields[0].toString() + ',' + fields[1].toString() + ',' + 
		            			fields[2].toString() + ',' + fields[3].toString() + ',' + fields[4].toString() + ',' + 
		            			fields[5].toString() + ',' + fields[6].toString() + ',' + fields[7].toString() + ',' +
		            			fields[8].toString() + ',' + fields[9].toString() + ',' + fields[10].toString() + ',' +
		            			fields[11].toString() + ',' + fields[12].toString() + '\n';
		            	
		            	w.write(registry);			            	
		             }
		             else {
		            	 bandera1 = 1;
		            	 
		            	 String lineu = fields[0].toString() + ',' + fields[1].toString() + ',' + 
		 	        			pokemon.getType1() + ',' + pokemon.getType2() + ',' + pokemon.getTotal() + ',' + 
		 	        			pokemon.getHp() + ',' + pokemon.getAttack() + ',' + pokemon.getDefense() + ',' +
		 	        			pokemon.getSpattack() + ',' + pokemon.getSpdefense() + ',' + pokemon.getSpeed() + ',' +
		 	        			pokemon.getGeneration() + ',' + pokemon.isLegendary() + '\n';
		            	 
		            	 w.write(lineu);
		             }
		            
		             line = br.readLine();
		         }
	        	}
		     }
	          
	          br.close();
	          w.close();
	    	  
	    	  if( bandera1 == 1 ) {
	    		
	    		//Delete old file pokemon.csv
		          File fichero = new File("csvpokemon/pokemon.csv");
		          
		          FileInputStream readFile = new FileInputStream(fichero);
		          readFile.close(); //garantizar que el archivo este cerrado
		          
		          if (fichero.delete())
		              System.out.println("El fichero ha sido borrado satisfactoriamente");
		          else {
		        	
		        	  System.out.println("El fichero no pudó ser borrado");
		        	  File ficherotmp = new File("csvpokemon/pokemontmp.csv");

		        	  ficherotmp.delete();		        	  
		          }		    	  
		         
		    	  //Rename file pokemontmp.csv
		          File f1 = new File("csvpokemon/pokemontmp.csv");
		          File f2 = new File("csvpokemon/pokemon.csv");
		          
		          if ( f1.renameTo(f2) )
		              System.out.println("El fichero ha sido renombrado satisfactoriamente");
		          else
		              System.out.println("El fichero no pudó ser renombrado");	    		  
	    	  }
	    	  else {
	    		  
	    		  File fichero = new File("csvpokemon/pokemontmp.csv");

		          if (fichero.delete())
		              System.out.println("El fichero ha sido borrado satisfactoriamente");
		          else
		              System.out.println("El fichero no pudó ser borrado");
	    	  }
	    	  
	         
	      } catch (Exception e) {
	    	  System.out.println(e);
	      } 
		
		
		return ResponseEntity.ok(pokemon);
	}
}
