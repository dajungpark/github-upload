package packwc;

import java.util.Scanner;

public class MenuViewer {
	static Scanner sc = new Scanner(System.in);
	
	 public static void showMenu() {
	     System.out.println("[ 1. �ֹ� | 2. �����ڸ�� | 3. ���α׷� ���� ]");
	     System.out.print("����: ");
	 }
	 
	 public static void showManagerMenu() {
		 System.out.println("[ 1. ȸ������ | 2. ������� | 3. �ֹ����� | 4. �����Ȳ | 5. ��ᱸ��  | 6. ��� | 7. ���θ޴� ]");
	     System.out.print("����: ");
	 }
	    
	 public static void showMemberMenu() {
	     System.out.println("[ 1. ȸ����� | 2. ȸ��������ȸ | 3. ȸ���������� | 4. ȸ���������� | 5. �� ȸ��  | 6. �����ڸ޴� ]");
	     System.out.print("����: ");
	 }
	    
    public static void showChickenMenu() {
    	System.out.println("������������������������������������������������������������������������������������[ �޴��� ]����������������������������������������������������������������������������������������������");
        System.out.println("�� 1. ����ġŲ	�� 2. �Ѹ�Ŭ	�� 3. �ڸ���ī	�� 4. ���ġŲ	�� 5. ����ġŲ	��		��");
        System.out.println("�� 6. ġŲ��	�� 7. �ҽ�	�� 8. ����Ƣ��	�� 9. �ݶ�	�� 10. ���̴�	�� 11. ����	��");
        System.out.println("�� 0. �ֹ� ����	��										��");
        System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
        System.out.print("����: ");
    }
    
    public static void showSunSalMenu() {
        System.out.println("[ 1. ���� | 2. �� ]");
        System.out.print("����: ");
    }  
}