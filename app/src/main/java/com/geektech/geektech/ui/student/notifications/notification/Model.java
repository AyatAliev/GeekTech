package com.geektech.geektech.ui.student.notifications.notification;

public class Model implements Contract.Model {
    @Override
    public String loadMessage() {
        return "Lorem Ipsum is simply dummy text of the printing and typesetting industry.\n" +
                "Lorem Ipsum has been the industry\n" +
                "standard dummy text ever since the 1500s, when an unknown printer \n" +
                "took a galley of type and scrambled it to make a type specimen book.\n" +
                "It has survived not only five centuries, but also the leap into electronic \n" +
                "typesetting, remaining essentially unchanged. It was popularised in the \n" +
                " 1960s with the release of Letraset sheets containing Lorem Ipsum passages,\n" +
                "and more recently with desktop publishing software like Aldus PageMaker \n" +
                "including versions of Lorem Ipsum";
    }
}
