package com.zhurawell.base.data.repo.dashboard;

import com.zhurawell.base.data.model.dashboard.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface DashboardRepo extends JpaRepository<Dashboard, BigInteger> {
}
