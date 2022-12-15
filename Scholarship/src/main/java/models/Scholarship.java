/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author WhaLeS
 */
public class Scholarship {
        int scholarshipFund;
        double averageNeededGrade;
        
        public Scholarship() {};
        public Scholarship(int scholarshipFund, double averageNeededGrade)
        {
            this.scholarshipFund = scholarshipFund;
            this.averageNeededGrade = averageNeededGrade;
        }
        
        public int getFund()
        {
            return scholarshipFund;
        }
        
        public double getGrade()
        {
            return averageNeededGrade;
        }
}
