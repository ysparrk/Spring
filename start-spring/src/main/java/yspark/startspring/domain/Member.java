// domain.Member.class
package yspark.startspring.domain;

public class Member {

    private Long id;  // id 식별자, id는 시스템이 정하는 임의의 값.
    private String name;  // name 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
