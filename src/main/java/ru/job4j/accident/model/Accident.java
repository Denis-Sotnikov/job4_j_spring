package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "accidents")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    private String name;
    private String text;
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private AccidentType type;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Rule> rules = new HashSet<>();

    public static Rule of(int id, String name) {
        Rule rule = new Rule();
        rule.setId(id);
        rule.setName(name);
        return rule;
    }

    public Accident() {
    }

    public Accident(int id, String name, String text, String address) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
    }

    public Accident(String name, String text, String address, AccidentType accidentType) {
        this.name = name;
        this.text = text;
        this.address = address;
        this.type = accidentType;
    }

    public Accident(String name, String text, String address, AccidentType accidentType, Set<Rule> list) {
        this.name = name;
        this.text = text;
        this.address = address;
        this.type = accidentType;
        this.rules = list;
    }

    public Accident(int id, String name, String text, String address, AccidentType accidentType,  Set<Rule> list) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
        this.type = accidentType;
        this.rules = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getType() {
        return type;
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id
                && Objects.equals(name, accident.name)
                && Objects.equals(text, accident.text)
                && Objects.equals(address, accident.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text, address);
    }

    @Override
    public String toString() {
        return "Accident{"
                + "id=" + id
                + ", name='" + name
                + '\''
                + ", text='" + text
                + '\''
                + ", address='" + address
                + '\''
                + ", type=" + type
                + ", rules=" + rules
                + '}';
    }
}