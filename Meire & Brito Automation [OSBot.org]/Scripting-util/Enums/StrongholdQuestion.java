package com.excellentscripts.api.util.enums;

public enum StrongholdQuestion {
    PHISHER("A website claims that they can make me a player moderator if I give them my password. What should I do?", "Don't tell them anything and inform Jagex through the game website"),
    STAY_LOGGED_IN("Can I leave my account logged in while I'm out of the room?", "No"),
    COOL_ADDON("My friend uses this great add-on program he got from a website, should I?", "No, it might steal my password"),
    LEND_ACCOUNT("My friend asks me for my password so that he can do a difficult quest for me", "Don't give him my password"),
    RECOVERY_QUESTIONS("Recovery answers should be..", "Memorable"),
    RECOVER_QUESTIONS_USED_FOR("What are your recovery questions used for?", "To recover my account if i don't remember my password"),
    BANK_PIN("What is a good example of a Bank PIN?", "The birthday of a famous person or event"),
    FREE_MEMBERS("What do you do if someone asks you for your password or recoveries to make you a member for free?", "Don't tell them anything and click the Report Abuse button"),
    MOD_PLS("What do you do if someone asks you for your password or recoveries to make you a player moderator?", "Don't tell them anything and click the Report Abuse button"),
    LOTTERY("What do you do if someone tells you that you have won the RuneScape lottery and asks you for your password and recoveries to award your prize?", "Don't tell them anything and click the Report Abuse button"),
    KEYLOGGER("What do I do if I think I have a keylogger or a virus?", "Virus scan my computer then change my password"),
    DETAILS_PLS("What do I do if a moderator asks me for my account details?", "Politely tell them no then use the report abuse button"),
    DEY_GOT_MY_RECOVS("What should I do if I think someone knows my recoveries?", "Use the recover a lost password section"),
    CHEATS("Where can I find cheats for RuneScape?", "Nowhere"),
    ENTER_PASSWORD("Where should I enter my password for RuneScape?", "Only on the RuneScape website"),
    BLOCK_PIN("Will Jagex block me for saying my PIN ingame?", "No"),
    GIVE_PASSWORD_PLS("Who can I give my password to?", "Nobody"),
    CHANGE_PIN("Who can I give my password to?", "Nobody"),
    WHY_RECOVER("Why do I need to type in recovery questions?", "To help me recover my password if I forget it or if it is stolen"),
    ;

    private final String question;

    private final String answer;

    StrongholdQuestion(String question, String answer) {
        this.question = question;
        this.answer= answer;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }
}
