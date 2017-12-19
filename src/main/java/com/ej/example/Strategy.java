package com.ej.example;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface Strategy {
    public PreparedStatement makePrepareStatement(Connection conn);
}
