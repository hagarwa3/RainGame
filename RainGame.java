//UIUC CS125 FALL 2014 MP. File: RainGame.java, CS125 Project: PairProgramming, Version: 2014-09-29T23:01:49-0500.492605769
/**
 * @author hagarwa3, tripath4
 * @reviewer sduri2, duri2
 */
public class RainGame {

	public static void main(String[] args) {
		// To get points type your netids above (CORRECTLY-Please double check your partner correctly spells your netid correctly!!)
		// Your netid is the unique part of your @illinois email address
		// Do not put your name or your UIN. 
		// REMEMBER TO COMMIT this file...
	
		int hs = 0, prevhs=0, blah = 0, ctr=5, m=0, x=0, y=0, dx=0, dy=0, score = 0, levelscore=0, level=1;
		String text = "";
		char ch = '0';
		long nTime = 0, elapsedTime = 0;
		boolean flag = false, set=false;
		String s="";
		
		nTime = System.currentTimeMillis();
		
		while (elapsedTime - nTime < 5000) {
			Zen.setFont("Helvetica-25");
			Zen.drawText("Welcome to (B)Rain Game.", 150 ,Zen.getZenHeight()/2-90 );
			Zen.drawText("Press any key to skip to Level 5.", 132 ,Zen.getZenHeight()/2 );
			String a = Zen.getEditText();
			if (a.length() > 0) {
				flag = true;
				level=5;
			}
			elapsedTime = System.currentTimeMillis();
		}
		Zen.setFont("Helvetica-40");

		while (Zen.isRunning()) {
			if (text.length() == 0) {
				//x = Zen.getZenWidth()/2;
				y = 0;
				dx = 0;
				dy = 5*level;
				text = "" + (int) (Math.random() * 9.999);
				m = text.charAt(0) - ch;
				x = ((Zen.getZenWidth()/10)*m)+25;				
			}
			Zen.setColor(0, 198, 255);
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight()/2);
			Zen.setColor(0, 0, 255);
			Zen.fillRect(0, Zen.getZenHeight()/2, Zen.getZenWidth(), Zen.getZenHeight());

			Zen.setColor(133, 0, 0);

			Zen.drawText(text, x, y);
			
			Zen.setColor(199, 0, 0);

			Zen.drawText("Level: "+level,0,35);
			Zen.drawText("Score: "+score,440,35);
			Zen.drawText("Lives: "+ctr, 220, 35);
			Zen.setFont("Helvetica-20");
			Zen.setColor(115, 230, 245);
			Zen.drawText("High Score: "+hs, 0, Zen.getZenHeight()-80);
			
			Zen.setFont("Helvetica-40");
			Zen.setColor(0, 92, 180);

			
			for (int i=0; i<10; i++) {
				blah = ((Zen.getZenWidth()/10)*i)+25;	
				s = ""+i;
				Zen.drawText(s, blah, Zen.getZenHeight()/2);
			}
			y += dy;
			
			
			//fallout
			if (y >= Zen.getZenHeight()) {
				y=0;
				score-=5;
				levelscore-=5;
				if(hs>prevhs)
				{
					hs-=5;
				}
				if (ctr==1) {
					nTime =System.currentTimeMillis();
					elapsedTime = 0;
					while (elapsedTime - nTime < 5000)
					{
					Zen.drawImage("raingameover.jpg", 0, 0, Zen.getZenWidth(), Zen.getZenHeight());
					Zen.drawText("Game Over",Zen.getZenWidth()/2-90 ,Zen.getZenHeight()-80 );
					elapsedTime = System.currentTimeMillis();
					Zen.drawText("Restarting in "+((int)((5000-(elapsedTime-nTime))/1000))+" seconds",Zen.getZenWidth()/2-170 ,Zen.getZenHeight()-30 );
					Zen.flipBuffer();
					}
					if (flag) {
					level = 5;
					}
					else {
						level = 1;
					}
					prevhs=hs;
					score = 0;
					levelscore = 0;
					ctr = 5;
					text = "";
					
				}
				else {
				ctr--;
				}
			}
			
			// Find out what keys the user has been pressing.
			String user = Zen.getEditText();
			// Reset the keyboard input to an empty string
			// So next iteration we will only get the most recently pressed keys.
			Zen.setEditText("");
			
			for(int i=0;i < user.length();i++) {
				char c = user.charAt(i);
				if(text.length()>0 && c == text.charAt(0))
					{
					score+=10;
					if (score>prevhs) {
						hs = score;
						set=true;
					}
					levelscore+=10;
					text = text.substring(1,text.length()); // all except first character
					}
			}
			Zen.flipBuffer();
			Zen.sleep(90);// sleep for 90 milliseconds
			if(levelscore>=50)
			{
				level++;
				nTime = System.currentTimeMillis();
				elapsedTime = 0;
				while (elapsedTime - nTime < 1000)
				{
					Zen.setColor(0, 0, 255);
					Zen.drawImage("raingamemess.jpg", 0, 0, Zen.getZenWidth(), Zen.getZenHeight());
					Zen.drawText("Next Level",Zen.getZenWidth()/2-90 ,Zen.getZenHeight()-80 );
					Zen.flipBuffer();
					elapsedTime = System.currentTimeMillis();
				}
			
				levelscore=0;
			}

		}
	}}

