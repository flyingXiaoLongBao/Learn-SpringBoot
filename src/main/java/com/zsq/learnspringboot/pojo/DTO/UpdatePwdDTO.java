package com.zsq.learnspringboot.pojo.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePwdDTO {
    private String old_pwd;
    private String new_pwd;
    private String re_pwd;
}
