package com.epam.mjc.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Map<String, String> profileData = getProfileData(file);
        return createProfileFromData(profileData);
    }

    private static Map<String, String> getProfileData(File file) {
        Map<String, String> profileData = new HashMap<>();
        try (var reader = new BufferedReader(new java.io.FileReader(file))) {
            while (reader.ready()) {
                var line = reader.readLine();
                String[] data = line.split(": ");
                profileData.put(data[0], data[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error occured while reading file");
            throw new RuntimeException(e);
        }
        return profileData;
    }

    private Profile createProfileFromData(Map<String, String> profileData) {
        String name = profileData.get("Name");
        String email = profileData.get("Email");
        int age = Integer.parseInt(profileData.get("Age"));
        long phone = Long.parseLong(profileData.get("Phone"));
        return new Profile(name, age, email, phone);
    }

    public static void main(String[] args) {
        var fileReader = new FileReader();
        System.out.println(fileReader.getDataFromFile(new File("src/main/resources/Profile.txt")));
    }
}