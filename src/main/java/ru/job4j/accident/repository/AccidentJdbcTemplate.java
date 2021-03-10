package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Repository
@Component
public class AccidentJdbcTemplate implements Store<Accident, String> {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public boolean update(Accident accident, List<String> rules) {
        jdbc.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement("update accident set name = ?, text = ?, address = ?, type_id = ? where id = ?");
                        ps.setString(1, accident.getName());
                        ps.setString(2, accident.getText());
                        ps.setString(3, accident.getAddress());
                        ps.setInt(4, accident.getType().getId());
                        ps.setInt(5, accident.getId());
                        return ps;
                    }
                });

        Set<Rule> ruleSet = new HashSet<>();
        for (String s : rules) {
            ruleSet.add(this.getListRules().get(Integer.parseInt(s) - 1));
        }

        jdbc.update("delete from accident_article_of_the_law where accident_id = ?", accident.getId());

        for (String s : rules) {
            jdbc.update("insert into accident_article_of_the_law (accident_id, article_id) values (?,?)",
                    accident.getId(), Integer.parseInt(s));
        }
        accident.setRules(ruleSet);
        return true;
    }

    @Override
    public List<Accident> getAll() {
        return jdbc.query("select accident.id, accident.name, text, address, accident.type_id as typeId,  t.name as tName from accident join types t on accident.type_id = t.id",
                (rs, row) -> {
                    System.out.println("row = " + row);
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    AccidentType type = AccidentType.of(rs.getInt("typeId"), rs.getString("tName"));
                    List<Rule> setRule = new ArrayList<>();
                    PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
                        @Override
                        public PreparedStatement createPreparedStatement(final Connection conn) throws SQLException {
                            final PreparedStatement ret = conn.prepareStatement("select a.id as aId, name from accident_article_of_the_law join articleofthelaw a on accident_article_of_the_law.article_id = a.id where accident_id = ?");
                            ret.setInt(1, accident.getId());
                            return ret;
                        }
                    };
                    setRule = jdbc.query(preparedStatementCreator,
                            (rw, rows) -> {
                                Rule rule = Rule.of(rw.getInt("aId"), rw.getString("name"));
                                return rule;
                            });
                    System.out.println("setRule = " + setRule);
                    accident.setType(type);
                    Set<Rule> ruleSet = new HashSet<>();
                    ruleSet.addAll(setRule);
                    accident.setRules(ruleSet);
                    return accident;
                });
    }

    @Override
    public Accident add(Accident accident, List<String> rules) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbc.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement("insert into accident (name, text, address, type_id) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                        ps.setString(1, accident.getName());
                        ps.setString(2, accident.getText());
                        ps.setString(3, accident.getAddress());
                        ps.setInt(4, accident.getType().getId());
                        return ps;
                    }
                },
                holder);

        accident.setId((Integer) holder.getKeys().get("id"));

        List<Rule> ruleList = jdbc.query("select * from articleofthelaw",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
        Set<Rule> ruleSet = new HashSet<>();
        for (String s : rules) {
            ruleSet.add(ruleList.get(Integer.parseInt(s) - 1));
        }

        for (String s : rules) {
            jdbc.update("insert into accident_article_of_the_law (accident_id, article_id) values (?,?)",
                    accident.getId(), Integer.parseInt(s));
        }

        accident.setRules(ruleSet);
        return accident;
    }

    @Override
    public Accident findById(int id) {
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(final Connection conn) throws SQLException {
                final PreparedStatement ret = conn.prepareStatement("select accident.id, accident.name, text, address, accident.type_id as typeId,  t.name as tName from accident join types t on accident.type_id = t.id where accident.id = ?");
                ret.setInt(1, id);
                return ret;
            }
        };
         return jdbc.query(creator,
                (rw, rows) -> {
                    Accident accident = new Accident();
                    accident.setId(rw.getInt("id"));
                    accident.setName(rw.getString("name"));
                    accident.setText(rw.getString("text"));
                    accident.setAddress(rw.getString("address"));

                    AccidentType type = AccidentType.of(rw.getInt("typeId"), rw.getString("tName"));
                    List<Rule> setRule = new ArrayList<>();
                    PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
                        @Override
                        public PreparedStatement createPreparedStatement(final Connection conn) throws SQLException {
                            final PreparedStatement ret = conn.prepareStatement("select a.id as aId, name from accident_article_of_the_law join articleofthelaw a on accident_article_of_the_law.article_id = a.id where accident_id = ?");
                            ret.setInt(1, accident.getId());
                            return ret;
                        }
                    };
                    setRule = jdbc.query(preparedStatementCreator,
                            (rs, rowses) -> {
                                Rule rule = Rule.of(rs.getInt("aId"), rs.getString("name"));
                                return rule;
                            });
                    accident.setType(type);
                    Set<Rule> ruleSet = new HashSet<>();
                    ruleSet.addAll(setRule);
                    accident.setRules(ruleSet);
                    return accident;
        }).get(0);
    }

    public List<Rule> getListRules() {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(final Connection conn) throws SQLException {
                final PreparedStatement ret = conn.prepareStatement("select * from articleofthelaw");
                return ret;
            }
        };
        return  jdbc.query(preparedStatementCreator,
                (rs, rowses) -> {
                    Rule rule = Rule.of(rs.getInt("id"), rs.getString("name"));
                    System.out.println("Rule rule = " + rule);
                    return rule;
                });
    }

    public List<AccidentType> getAccidentTypes() {
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(final Connection conn) throws SQLException {
                final PreparedStatement ret = conn.prepareStatement("select * from types");
                return ret;
            }
        };
        return  jdbc.query(preparedStatementCreator,
                (rs, rowses) -> {
                    AccidentType accidentType = AccidentType.of(rs.getInt("id"), rs.getString("name"));
                    return accidentType;
                });
    }
}