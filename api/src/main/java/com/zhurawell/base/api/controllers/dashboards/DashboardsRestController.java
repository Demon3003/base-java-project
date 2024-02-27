package com.zhurawell.base.api.controllers.dashboards;


import com.fasterxml.jackson.databind.node.BigIntegerNode;
import com.zhurawell.base.data.model.dashboard.Dashboard;
import com.zhurawell.base.data.repo.dashboard.DashboardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardsRestController {

    @Autowired
    private DashboardRepo dashboardRepo;

    @GetMapping("/get/{id}")
    public ResponseEntity getDashboardById(@PathVariable("id") BigInteger id) {
        Dashboard d = dashboardRepo.getById(id);
        return ResponseEntity.ok().body(d);
    }
}
