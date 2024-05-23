package lb.project.lb6_server.server.logic.aspects;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.server.logic.commands.Command;
import lb.project.lb6_server.server.logic.commands.Register;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommandAspect {

    @Around("@annotation(AuthorizationCheck)")
    public Object addAuthorizationCheck(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] paramValues = joinPoint.getArgs();
        Command target = (Command) joinPoint.getTarget();
        if(target.isUserExists((User)paramValues[1]) || (target instanceof Register))
            return joinPoint.proceed();


        return null;
    }
}
