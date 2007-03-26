package net.kodveus.kumanifest.utility.calc;

/////////////////////////////////////////////////////////////////////
//
// Pocket Calculator emulates a pocket calculator of the simplest
// kind. It was Mikael Bonnier's first Java program.
//
//     Pocket Calculator v1.1 is Freeware
//     Commercial Distribution Restricted
//     Copyright (C) 1995-1996 by Mikael Bonnier, Lund, Sweden.
//
//
// It was developed using JDK-1.0 on Windows 95.
//
// Howto compile & run:
// 1. Install JDK-1.0 if not already done. You can download this
//    from http://java.sun.com in Developer's Corner.
// 2. Save PocketCalc.java and a html-file with this tag:
//       <APPLET CODE="PocketCalc.class" WIDTH="395" HEIGHT="179">
//       <PARAM NAME="BGCOLOR" VALUE="#FF8000">
//       </APPLET>
//    in a file folder and at the command prompt enter:
// 3. javac PocketCalc.java
// 4. appletviewer pocketcalc.html
// 5. Now you may try out all the features of the calculator.
// 6. You may also run it as an application using: java PocketCalc
//
// Tips:
// *  Read the newsgroups comp.lang.java.*.
// *  The Java documentation is available in Windows Help format
//    from http://www.dippybird.com.
//
// Thanks are due to many people for reporting bugs and suggestions,
// especially: Luis Fernandes, Bengt Ask, and Olivier Perrin.
//
// Revision history:
// 1995-Dec: 1.0bX  Beta versions.
// 1996-Jan: 1.0    All keys implemented and an X Windows problem
//                  fixed.
// 1996-Jul: 1.01   Fixed bugs of unary operations after equals
//                  operations.
// 1996-Nov: 1.1    Added parameter BGCOLOR. Added support to run
//                  as an application.
// 2006-Mar: 1.2	Arkaplan rengi (BGCOLOR) kaldirildi. Applet yerine Frame kullanildi
//					Sinif yapisi degistirildi.Klavye destegi eklendi (T.Emre KALAYCI)
//
// Suggestions, improvements, and bug-reports
// are always welcome to:
//                  Mikael Bonnier
//                  Osten Undens gata 88
//                  SE-227 62  LUND
//                  SWEDEN
//
// Or use my internet addresses:
//                  mikaelb@df.lth.se
//                  http://www.df.lth.se/~mikaelb/
//              _____
//             /   / \
// ***********/   /   \***********
//           /   /     \
// *********/   /       \*********
//         /   /   / \   \
// *******/   /   /   \   \*******
//       /   /   / \   \   \
// *****/   /   /***\   \   \*****
//     /   /__ /_____\   \   \
// ***/               \   \   \***
//   /_________________\   \   \
// **\                      \  /**
//    \______________________\/
//
// Mikael Bonnier
/////////////////////////////////////////////////////////////////////

import java.awt.Button;
import java.awt.Event;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Calculator extends JFrame implements KeyListener {

	public Calculator() {
		init();
	}

	private static final long serialVersionUID = 1L;

	TextField txtDisp;

	public final int OP_NONE = 0;

	public final int OP_ADD = 1;

	public final int OP_SUB = 2;

	public final int OP_MUL = 3;

	public final int OP_DIV = 4;

	public final int OP_NEG = 5;

	public final int OP_SQRT = 6;

	public final int OP_EQ = 7;

	public final int OP_C = 8;

	public final int OP_AC = 9;

	public final int OP_MC = 10;

	public final int OP_MR = 11;

	public final int OP_MM = 12;

	public final int OP_MP = 13;

	public final int OP_PCT = 14;

	public final int OP_DEC = -1;

	String msDecimal;

	int mnOp = OP_NONE;

	boolean mbNewNumber = true;

	boolean mbDecimal = false;

	double mdReg = 0.0;

	double mdMemory = 0.0;

	boolean mbConstant = false;

	double mdConstant = 0.0;

	int mnConstantOp = OP_NONE;

	boolean mbPercent = false;

	double mdFirst = 0.0;

	public void init() {
		CalcButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
		CalcButton btnDecSep, btnNeg, btnSqrt, btnPlus, btnMinus;
		CalcButton btnTimes, btnDiv, btnEqual, btnClear, btnAllClear;
		CalcButton btnMemoryClear, btnMemoryRecall, btnMemoryMinus, btnMemoryPlus;
		CalcButton btnPercent;

		setLayout(null);
		setFont(new Font("Helvetica", Font.PLAIN, 14));

		btn0 = new CalcButton("0", OP_NONE, 0);
		btn0.setBounds(64, 144, 96, 24);
		add(btn0);

		btn1 = new CalcButton("1", OP_NONE, 1);
		btn1.setBounds(64, 112, 40, 24);
		add(btn1);

		btn2 = new CalcButton("2", OP_NONE, 2);
		btn2.setBounds(120, 112, 40, 24);
		add(btn2);

		btn3 = new CalcButton("3", OP_NONE, 3);
		btn3.setBounds(176, 112, 40, 24);
		add(btn3);

		btn4 = new CalcButton("4", OP_NONE, 4);
		btn4.setBounds(64, 80, 40, 24);
		add(btn4);

		btn5 = new CalcButton("5", OP_NONE, 5);
		btn5.setBounds(120, 80, 40, 24);
		add(btn5);

		btn6 = new CalcButton("6", OP_NONE, 6);
		btn6.setBounds(176, 80, 40, 24);
		add(btn6);

		btn7 = new CalcButton("7", OP_NONE, 7);
		btn7.setBounds(64, 48, 40, 24);
		add(btn7);

		btn8 = new CalcButton("8", OP_NONE, 8);
		btn8.setBounds(120, 48, 40, 24);
		add(btn8);

		btn9 = new CalcButton("9", OP_NONE, 9);
		btn9.setBounds(176, 48, 40, 24);
		add(btn9);

		btnDecSep = new CalcButton("·", OP_NONE, OP_DEC);
		btnDecSep.setBounds(176, 144, 40, 24);
		add(btnDecSep);

		btnNeg = new CalcButton("+/-", OP_NEG, 0);
		btnNeg.setBounds(8, 48, 40, 24);
		add(btnNeg);

		btnSqrt = new CalcButton("Sqrt", OP_SQRT, 0);
		btnSqrt.setBounds(8, 80, 40, 24);
		add(btnSqrt);

		btnPlus = new CalcButton("+", OP_ADD, 0);
		btnPlus.setBounds(232, 112, 40, 56);
		add(btnPlus);

		btnMinus = new CalcButton("-", OP_SUB, 0);
		btnMinus.setBounds(288, 112, 40, 24);
		add(btnMinus);

		btnTimes = new CalcButton("×", OP_MUL, 0);
		btnTimes.setBounds(232, 80, 40, 24);
		add(btnTimes);

		btnDiv = new CalcButton("÷", OP_DIV, 0);
		btnDiv.setBounds(288, 80, 40, 24);
		add(btnDiv);

		btnEqual = new CalcButton("=", OP_EQ, 0);
		btnEqual.setBounds(288, 144, 40, 24);
		add(btnEqual);

		btnClear = new CalcButton("C", OP_C, 0);
		btnClear.setBounds(8, 112, 40, 24);
		add(btnClear);

		btnAllClear = new CalcButton("AC", OP_AC, 0);
		btnAllClear.setBounds(8, 144, 40, 24);
		add(btnAllClear);

		btnMemoryClear = new CalcButton("MC", OP_MC, 0);
		btnMemoryClear.setBounds(344, 48, 40, 24);
		add(btnMemoryClear);

		btnMemoryRecall = new CalcButton("MR", OP_MR, 0);
		btnMemoryRecall.setBounds(344, 80, 40, 24);
		add(btnMemoryRecall);

		btnMemoryMinus = new CalcButton("M-", OP_MM, 0);
		btnMemoryMinus.setBounds(344, 112, 40, 24);
		add(btnMemoryMinus);

		btnMemoryPlus = new CalcButton("M+", OP_MP, 0);
		btnMemoryPlus.setBounds(344, 144, 40, 24);
		add(btnMemoryPlus);

		btnPercent = new CalcButton("%", OP_PCT, 0);
		btnPercent.setBounds(232, 48, 40, 24);
		add(btnPercent);

		txtDisp = new TextField("0", 80);
		txtDisp.addKeyListener(this);
		txtDisp.setEditable(false);
		add(txtDisp);
		txtDisp.setBounds(64, 8, 268, 31);

		String sOneTenth = (new Double(0.1)).toString();
		msDecimal = sOneTenth.substring(sOneTenth.length() - 2).substring(0, 1);

		//
		addKeyListener(this);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(401, 205);
		this.validate();
		this.setVisible(true);
	}

	public void append(int nValue) {
		String sDigit;

		if (nValue == OP_DEC)
			if (!mbDecimal) {
				if (mbNewNumber) {
					txtDisp.setText("0");
					mbNewNumber = false;
				}
				mbDecimal = true;
				sDigit = msDecimal;
			} else
				return;
		else
			sDigit = (new Integer(nValue)).toString();
		if (mbNewNumber) {
			txtDisp.setText(sDigit);
			if (nValue != 0)
				mbNewNumber = false;
		} else
			txtDisp.setText(txtDisp.getText() + sDigit);
		repaint();
		txtDisp.requestFocus();
	}

	public void doOp(int nNewOp) {
		double dDisp;

		dDisp = (new Double(txtDisp.getText())).doubleValue();
		if (mbPercent)
			if (nNewOp != OP_ADD && nNewOp != OP_SUB)
				mbPercent = false;
		if (!mbPercent)
			switch (nNewOp) {
			case OP_ADD:
			case OP_SUB:
			case OP_MUL:
			case OP_DIV:
				if (mbNewNumber) {
					if (nNewOp == mnOp && !mbConstant) {
						mbConstant = true;
						mdConstant = dDisp;
						mnConstantOp = nNewOp;
					} else
						mbConstant = false;
				} else
					mbConstant = false;
			case OP_EQ:
			case OP_MM:
			case OP_MP:
			case OP_PCT:
				if (!mbNewNumber || isEqOp(nNewOp)) {
					if (mbConstant) {
						mdReg = mdConstant;
						mnOp = mnConstantOp;
					}
					mbPercent = nNewOp == OP_PCT;
					if (mbPercent)
						mdFirst = mdReg;
					switch (mnOp) {
					case OP_ADD:
						mdReg = mdReg + dDisp;
						break;
					case OP_SUB:
						mdReg = mdReg - dDisp;
						break;
					case OP_MUL:
						mdReg = mdReg * dDisp;
						break;
					case OP_DIV:
						mdReg = mdReg / dDisp;
						break;
					case OP_EQ:
					case OP_MM:
					case OP_MP:
					case OP_PCT:
					case OP_NONE:
						mdReg = dDisp;
						break;
					}
					if (mbPercent)
						mdReg /= 100;
					txtDisp.setText((new Double(mdReg)).toString());
				}
				mnOp = nNewOp;
				mbNewNumber = true;
				mbDecimal = false;
				break;
			}
		switch (nNewOp) {
		case OP_ADD:
			if (mbPercent) {
				mdReg = mdFirst + mdReg;
				txtDisp.setText((new Double(mdReg)).toString());
				mbPercent = false;
			}
			break;
		case OP_SUB:
			if (mbPercent) {
				mdReg = mdFirst - mdReg;
				txtDisp.setText((new Double(mdReg)).toString());
				mbPercent = false;
			}
			break;
		case OP_NEG:
			dDisp = -dDisp;
			txtDisp.setText((new Double(dDisp)).toString());
			if (isEqOp(mnOp)) {
				mdReg = dDisp;
			}
			break;
		case OP_SQRT:
			dDisp = Math.sqrt(dDisp);
			txtDisp.setText((new Double(dDisp)).toString());
			if (isEqOp(mnOp)) {
				mdReg = dDisp;
			}
			mbNewNumber = true;
			mbDecimal = false;
			break;
		case OP_C:
			dDisp = 0.0;
			txtDisp.setText("0");
			if (isEqOp(mnOp)) {
				mdReg = dDisp;
			}
			mbNewNumber = true;
			mbDecimal = false;
			break;
		case OP_AC:
			txtDisp.setText("0");
			mnOp = OP_NONE;
			mbNewNumber = true;
			mbDecimal = false;
			mdReg = 0.0;
			mbConstant = false;
			break;
		case OP_MC:
			mdMemory = 0.0;
			break;
		case OP_MR:
			dDisp = mdMemory;
			txtDisp.setText((new Double(dDisp)).toString());
			if (isEqOp(mnOp)) {
				mdReg = dDisp;
			}
			mbNewNumber = true;
			mbDecimal = false;
			break;
		case OP_MM:
			mdMemory -= mdReg;
			break;
		case OP_MP:
			mdMemory += mdReg;
			break;
		}
		txtDisp.requestFocus();
	}

	protected boolean isEqOp(int nOp) {
		return nOp == OP_EQ || nOp == OP_MM || nOp == OP_MP || nOp == OP_PCT;
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
		char keyChar = e.getKeyChar();
		if (Character.isDigit(keyChar)) {
			append(Integer.parseInt(Character.toString(keyChar)));
			e.consume();
			return;
		}
		if (keyChar == msDecimal.toCharArray()[0]) {
			append(OP_DEC);
			return;
		}
		switch (keyChar) {
		case '+':
			doOp(OP_ADD);
			break;
		case '-':
			doOp(OP_SUB);
			break;
		case '*':
			doOp(OP_MUL);
			break;
		case '/':
			doOp(OP_DIV);
			break;
		case '%':
			doOp(OP_PCT);
			break;
		case KeyEvent.VK_SPACE:
			doOp(OP_C);
			break;
		case '=':
		case KeyEvent.VK_ENTER:
			doOp(OP_EQ);
			break;
		}
	}

	class CalcButton extends Button {
		private static final long serialVersionUID = 1L;

		int mnOp;

		int mnValue;

		CalcButton(String sText, int nOp, int nValue) {
			super(sText);
			mnOp = nOp;
			mnValue = nValue;
		}

		public boolean action(Event evt, Object arg) {
			if (mnOp == OP_NONE)
				append(mnValue);
			else
				doOp(mnOp);
			return true;
		}
	}
}