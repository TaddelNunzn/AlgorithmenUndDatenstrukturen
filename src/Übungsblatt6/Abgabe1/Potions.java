package Übungsblatt6.Abgabe1;

public class Potions {

	
	public static class Ingredient {

		String name;
		int cost;
		
		public Ingredient(String name, int cost) {
			this.name = name;
			this.cost = cost;
		}
		
		// use this method to compare two ingredients.
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || o.getClass() != this.getClass() ) {
				return false;
			}
			Ingredient i = (Ingredient) o;
			return name == i.name && cost == i.cost;
		}
		
	}
	
	
	public static class Step {

		Ingredient[] input;   // the step's input ingredients (1:many)
		Ingredient output;    // the step's output ingredient (exactly 1)
		
		public Step(Ingredient output, Ingredient[] input) {
			this.output = output;
			this.input = input;
		}
		
		// prints a step
		public void print() {
			System.out.print("  <STEP> ");
			for (Ingredient i : input) {
				System.out.print(i.name + ", ");
			}
			System.out.println(" -> " + output.name + " </STEP>");
		}

		// prints a sequence of steps (e.g., a recipe).
		public static void print(Step[] steps) {
			System.out.println("<STEPS>");
			for (Step s : steps) {
				s.print();
			}
			System.out.println("</STEPS>");
		}

	}

	// checks if an ingredient is available on the table 
	// or in the pantry.
	public static boolean available(Ingredient ing,
									Ingredient[] place) {
		for (Ingredient ing2 : place) {
			if (ing.equals(ing2))
				return true;
		}
		return false;
	}
	
	// removes an ingredient from the table or pantry.
	public static Ingredient[] remove(Ingredient ing, 
									  Ingredient[] place) {
		Ingredient[] result = new Ingredient[place.length - 1];
		boolean done = false;
		int c = 0;
		for (int i=0; i<place.length; ++i) {
			if (!done && ing.equals(place[i])) {
				done = true;
			} else {
				result[c++] = place[i];
			}
		}
		assert(done);   // assertion if ing not in place.
		return result;
	}
	
	// adds a new ingredient 'ing' to the table or pantry.
	public static Ingredient[] add(Ingredient ing,
								   Ingredient[] place) {
		Ingredient[] result = new Ingredient[place.length + 1];
		result[0] = ing;
		for (int i=0; i<place.length; ++i) {
			result[i+1] = place[i];
		}
		return result;
	}

	public static Step[] addStep(Step newStep, Step[] recipe){
		Step[] result = new Step[recipe.length+1];
		result[0]=newStep;
		for(int i=0; i<recipe.length; i++){
			result[i+1] = recipe[i];
		}
		return result;
	}

	/*
	 * Create a potion recipe using a greedy strategy.
	 *
	 *  @param goal : The potion to produce.
	 *  @param book : The book containing the possible brewing steps.
	 *  @param pantry: The reserve of available ingredients.
	 */
	public static Step[] greedy(Ingredient goal,
								Step[] book,
								Ingredient[] pantry) {
		Ingredient goals[] = {goal};
		Step[] recipe = new Step[0];

		check: while(goals.length>0){
			Ingredient ing = goals[0];
			if(available(ing,pantry)){
				goals = remove(ing,goals);
				pantry = remove(ing,pantry);
			}else{
				for(var b:book){
					if(b.output.equals(ing)){
						recipe = addStep(new Step(ing,b.input),recipe);
						goals = remove(ing,goals);
						for(var i: b.input){
							goals = add(i,goals);
						}
						continue check;
					}
				}
				return new Step[0];
			}
		}
		return recipe;
	}
	
	
	/*
	 * Create a potion recipe using a backtracking strategy.
	 * 
	 *  @param goal : The potion to produce.
	 *  @param book : The book containing the possible brewing steps.
	 *  @param pantry: The reserve of available ingredients.
	 */	
	public static Step[] backtracking(Ingredient goal, 
						              Step[] rulebook,
							          Ingredient[] pantry) {
		Step[] recipe=null;
		return recipe;
		 // FIXME: internal recursive methods are allowed!
	}
	
	
}

