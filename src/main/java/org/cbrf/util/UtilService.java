package org.cbrf.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@AllArgsConstructor
@Getter
@Setter
public class UtilService {

    private Scanner scanner;

    public int getChoice(){
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= 0 && choice <= 5) {
                System.out.println("Вы выбрали: " + choice);
                return choice;
            }
            System.out.println("Пожалуйста, выберите другое действие.");
        } else {
            System.out.println("Некорректный ввод. Пожалуйста, введите число от 0 до 5.");
            scanner.nextLine();
        }
        return -1;
    }
}
