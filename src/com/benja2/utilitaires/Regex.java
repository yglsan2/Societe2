package com.benja2.utilitaires;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Regex {



    // RegEx pour le numéro de téléphone (France, DOM-TOM, et internationaux)
    public static final Pattern PATTERN_TELEPHONE = Pattern.compile( "^(?:\\+\\d{1,4}[\\s.-]?)?(?:0|\\d{1,4}[\\s.-]?)?[1-9](?:[\\s.-]?\\d{2}){4,5}$" );


    // RegEx pour le code postal (5 chiffres)
    public static final Pattern PATTERN_CODE_POSTAL = Pattern.compile("^\\d{5}$");




    // RegEx de l'email (domaine jusqu'à 63 caractères)
    public static final Pattern PATTERN_EMAIL = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,63}$", Pattern.CASE_INSENSITIVE);


    // Pattern du format de date jj/mm/aaaa
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy"); }