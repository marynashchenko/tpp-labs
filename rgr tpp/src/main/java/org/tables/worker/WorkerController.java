package org.tables.worker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tables.common.BaseController;
import org.tables.user.UserService;

@RestController
@RequestMapping("/worker")
public class WorkerController extends BaseController<Worker, WorkerService> {

        public WorkerController(WorkerService service, UserService userService) {
            super(service, userService);
        }
}
