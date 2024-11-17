package org.tables.profession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tables.common.BaseController;
import org.tables.user.UserService;

@RestController
@RequestMapping("/profession")
public class ProfessionController extends BaseController<Profession, ProfessionService> {

        public ProfessionController(ProfessionService service, UserService userService) {
            super(service, userService);
        }
}
