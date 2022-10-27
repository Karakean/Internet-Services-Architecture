package com.example.isa;

import com.example.isa.entity.Family;
import com.example.isa.entity.Species;
import com.example.isa.services.FamilyService;
import com.example.isa.services.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Runner implements CommandLineRunner {
    FamilyService familyService;
    SpeciesService speciesService;

    @Autowired
    public Runner(FamilyService familyService, SpeciesService speciesService) {
        this.familyService = familyService;
        this.speciesService = speciesService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        while(!exit){
            displayMenu();
            String input = reader.readLine();
            switch (input) {
                case "1" -> familyService.findAll().forEach(System.out::println);
                case "2" -> speciesService.findAll().forEach(System.out::println);
                case "3" -> saveSpeciesView(reader);
                case "4" -> deleteSpeciesView(reader);
                case "5" -> exit = true;
                default -> System.out.println("It's not a valid option.");
            }
        }
    }

    public void displayMenu(){
        System.out.println();
        System.out.println("MENU");
        System.out.println("1. Show all families");
        System.out.println("2. Show all species");
        System.out.println("3. Add a new species");
        System.out.println("4. Remove a species");
        System.out.println("5. Exit");
    }

    public void saveSpeciesView(BufferedReader reader) throws IOException {
        long ID = 1; //an existing ID
        while(speciesService.find(ID) != null){
            System.out.println("Type a valid ID:");
            ID = Long.parseLong(reader.readLine());
        }
        System.out.println("Type a name:");
        String name = reader.readLine();
        Family chosenFamily;
        while (true) {
            System.out.println("Choose a family key: ");
            familyService.findAll().forEach(System.out::println);
            String input = reader.readLine();
            chosenFamily = familyService.find(Long.parseLong(input));
            if (chosenFamily != null ) {
                break;
            }
            System.out.println("It's not a valid family key.");
        }
        System.out.println("Is it hallucinogenic? Type \"yes\" or \"no\"");
        int answerState = -1;
        while(answerState == -1) {
            switch (reader.readLine().toLowerCase()) {
                case "yes" -> answerState = 1;
                case "no" -> answerState = 0;
                default -> System.out.println("It's not a valid answer");
            }
        }
        speciesService.save(new Species(ID, name, chosenFamily, (answerState == 1)));
        System.out.println("The species added successfully.");
        return;
    }
    public void deleteSpeciesView(BufferedReader reader) throws IOException {
        System.out.println("Select species that you would like to delete:");
        speciesService.findAll().forEach(System.out::println);
        String input = reader.readLine();
        while(speciesService.find(Long.parseLong(input)) == null){
            System.out.println("There is no species with such key. Please choose a valid key.");
            input = reader.readLine();
        }
        speciesService.delete(speciesService.find(Long.parseLong(input)));
        System.out.println("The species deleted successfully.");
    }
}
