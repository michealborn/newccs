package zz.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Time 2019/4/2
 * @Author zlian
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        //mock一个restful请求
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                get("/user")
                        .param("userName","micheal")
                        .param("size","15")
                        .param("page","2")
                        .param("sort","age,desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        //发起这个请求，期望的值在andExpect内
        String result = mockMvc.perform(mockHttpServletRequestBuilder)
                //返回的是200
                .andExpect(status().isOk())
                //返回的是集合，长度为3
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenGetInfoSucess() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8);
        String result = mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("micheal"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenCreateSuccess() throws Exception {

        Date date = new Date();
        System.out.println(date.getTime());
        String content = "{\"userName\":\"micheal\",\"passWord\":null,\"birthday\":"+date.getTime()+"}";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content);
        String result = mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {

        Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        System.out.println(date.getTime());
        String content = "{\"id\":\"1\",\"userName\":\"micheal\",\"passWord\":null,\"birthday\":"+date.getTime()+"}";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content);
        String result = mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8);
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk());
    }


}
