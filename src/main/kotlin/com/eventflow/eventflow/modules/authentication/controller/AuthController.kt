package com.eventflow.eventflow.modules.authentication.controller

import com.eventflow.eventflow.dao.BaseResponse
import com.eventflow.eventflow.exception.EventFlowUniqueException
import com.eventflow.eventflow.modules.authentication.dao.LoginRequest
import com.eventflow.eventflow.modules.authentication.dao.RegisterRequest
import com.eventflow.eventflow.modules.authentication.utils.JwtUtils
import com.eventflow.eventflow.modules.users.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils,
    private val userService: UserService
) {
    private val logger: Logger = LoggerFactory.getLogger(AuthController::class.java)

    @PostMapping("/auth/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<BaseResponse> {
        return try {
            val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    loginRequest.email,
                    loginRequest.password
                )
            )

            ResponseEntity.ok(BaseResponse.ofMessage(jwtUtils.generateJwtToken(authentication)))
        } catch (e: BadCredentialsException) {
            logger.warn("[login] - bad credentials - {}", e.message)
            ResponseEntity.badRequest().body(BaseResponse.ofBadRequest("Invalid credentials"))
        } catch (e: Exception) {
            logger.error("[login] - error", e)
            ResponseEntity.internalServerError().body(BaseResponse.ofError("Could not authenticate"))
        }

    }

    @PostMapping("/auth/register")
    @ResponseBody
    fun register(@RequestBody registerRequest: RegisterRequest): ResponseEntity<BaseResponse> {
        return try {
            userService.register(registerRequest)
            ResponseEntity.ok(BaseResponse())
        } catch (e: EventFlowUniqueException) {
            logger.warn("[register] - non unique email {}", e.message)
            ResponseEntity.badRequest().body(BaseResponse.ofBadRequest("Email already used"))
        } catch (e: Exception) {
            logger.error("[register] - could not register", e)
            ResponseEntity.internalServerError().body(BaseResponse.ofError("Could not register"))
        }
    }

}