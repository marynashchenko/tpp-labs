package org.tables.zhek;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tables.common.BaseController;
import org.tables.user.UserService;

@RestController
@RequestMapping("/zhek")
public class ZhekController extends BaseController<Zhek, ZhekService> {

        public ZhekController(ZhekService service, UserService userService) {
            super(service, userService);
        }
}
