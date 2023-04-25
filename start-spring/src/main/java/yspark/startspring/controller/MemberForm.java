package yspark.startspring.controller;
// 웹 등록 화면에서 데이터를 전달 받을 폼 개체
public class MemberForm {
    private String name; // createMemberForm에서 받아온다록

    // getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
