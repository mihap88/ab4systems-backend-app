
public class FactoryProducer {
   public static AbstractPlaceFactory getFactory(String choice){
   
	   /*
	    * Sectiunea asta va fi extinsa cu "if else"-uri pentru fiecare
	    * noua implementare PlaceFactory a lui AbstractPlaceFactory
	    * Acest design al claselor ne va ajuta la adaugarea extrem de
	    * usoara a nivelelor in ierarhie
	    */
      if(choice.equalsIgnoreCase("PLACE_V1")){
         return new PlaceFactory();
      }
      
      return null;
   }
}