package com.example.feedbackapp.Adapter;

import com.example.feedbackapp.model.Class;
import com.example.feedbackapp.model.HeaderRecycleView;
import com.example.feedbackapp.model.Module;
import com.example.feedbackapp.model.Question;
import com.example.feedbackapp.model.Topic;
import com.example.feedbackapp.model.Trainee;

import java.util.ArrayList;
import java.util.List;

public class ClassDataUtils {

    public static List<Class> getClasss( ) {
        List <Class> list = new ArrayList<Class>();
        for(int i=1; i<=5; i++){
            Class clas = new Class(i,"Class" + i);
            list.add(clas);
        }
        return list;
    }
    public static List<Module> getModule( ) {
        List <Module> list = new ArrayList<Module>();
        for(int i=1; i<=5; i++){
            Module module = new Module(i,"Module" + i);
            list.add(module);
        }
        return list;
    }
// Has 4 static toppic
    public static ArrayList<Topic> getTopic(){
        ArrayList <Topic> list = new ArrayList<Topic>();
        Topic topic0 = new Topic(0,"Header");
        Topic topic1 = new Topic(1,"1. Training program & content");
        Topic topic2 = new Topic(2,"2. Training/Coachs");
        Topic topic3 = new Topic(3,"3. Course organization");
        Topic topic4= new Topic(4,"4. Other");
        Topic topic5 = new Topic(5,"Footer");
        list.add(topic0);
        list.add(topic1);
        list.add(topic2);
        list.add(topic3);
        list.add(topic4);
        list.add(topic5);
        return list;
    }
    public static ArrayList<Topic> getTopicForDetail(){
        ArrayList <Topic> list = new ArrayList<Topic>();
        Topic topic1 = new Topic(1,"I. Training program & content");
        Topic topic2 = new Topic(2,"II. Training/Coachs");
        Topic topic3 = new Topic(3,"II. Course organization");
        Topic topic4= new Topic(4,"IV. Other");
        list.add(topic1);
        list.add(topic2);
        list.add(topic3);
        list.add(topic4);
        return list;
    }
    public static Trainee getTrainee(){
            Trainee trainee = new Trainee("Username","Trainee");
        return trainee;
    }
//para: topicID: 1->4
// brief: get question by topicID
   /* public static ArrayList<Question> getQuestion(int topicID)
    {
        // get question
        ArrayList <Question> list = new ArrayList<Question>();

            for(int i=1; i<= 4; i++){
                Question module = new Question(i,topicID,topicID + "." + i + " Question " + i + " of topic " + topicID);
                list.add(module);
            }

        return list;
    }
    public static ArrayList<Question> getQuestionDetail(int topicID)
    {
        // get question
        ArrayList <Question> list = new ArrayList<Question>();

        for(int i=1; i<= 4; i++){
            Question module = new Question(i,topicID, "- Question " + i + " of topic " + (topicID + 1));
            list.add(module);
        }

        return list;
    }*/
// header recycleView
    public static HeaderRecycleView getHeaderRecycleView()
    {
        HeaderRecycleView headerRecycleView = new HeaderRecycleView("Phạm Đức Huy", "Truyền thông và mạng máy tính", "Class 12");
        return headerRecycleView;
    }
}

