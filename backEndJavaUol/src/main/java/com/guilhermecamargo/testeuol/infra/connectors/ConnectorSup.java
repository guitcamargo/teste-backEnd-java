package com.guilhermecamargo.testeuol.infra.connectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;
import java.lang.annotation.Annotation;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guilhermecamargo.testeuol.domain.entities.dto.Grupo;
import com.guilhermecamargo.testeuol.domain.entities.dto.LigaDaJusticaResponse;
import com.guilhermecamargo.testeuol.domain.enums.EnumGrupo;
import com.guilhermecamargo.testeuol.domain.enums.EnumRequest;

/**
 * Classe responsável por realizar as requisições
 * E deserealizar a resposta de acordo com os params.
 * @author guicamargo
 */
@Service
public class ConnectorSup implements Serializable{
	
	
	 public Grupo getObjectRequest(EnumGrupo enumGrupo) {
	        Grupo grupo = null;
	        try {
	            String json = this.getResponse(enumGrupo.getEnumRequest());
	            Object o = this.textToObject(json, enumGrupo.getEnumRequest(), enumGrupo.getEnumRequest().getTypeConvert());
	            if(enumGrupo.equals(EnumGrupo.VINGADORES)){
	                grupo = (Grupo) o;
	            }else{
	                LigaDaJusticaResponse s = (LigaDaJusticaResponse) o;
	                grupo = s.getGrupo();
	            }
	        }catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return grupo;
	    }

	    private String getResponse(EnumRequest request){
	        String valorResposta = "";
	        try{
	            URL url = new URL(request.getUrl());
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            conn.setRequestProperty("Accept", request.getAcceptRequest());

	            if (conn.getResponseCode() != 200) {
	                throw new Exception();
	            }

	            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
	                String line = "";
	                StringBuilder resposta = new StringBuilder();

	                while((line = in.readLine()) != null){
	                    resposta.append(line);
	                }

	                valorResposta = resposta.toString();

	                if (valorResposta.isEmpty()) {
	                    throw new Exception();
	                }

	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }

	        return valorResposta;
	    }

	    private <T> T textToObject(String text, EnumRequest typeV, Class<T> typeC) throws Exception {
	        if(Objects.isNull(typeV)){
	            throw new Exception("ee");
	        }

	        if(typeV.equals(EnumRequest.JSON)){
	            return this.convertJsonToObject(text, typeC);
	        }else{
	            return this.convertXmlToObject(text, typeC);
	        }
	    }

	    private <T> T convertJsonToObject(String json, Class<T> typeC) throws IOException {
	        return new ObjectMapper().readValue(json, typeC);
	    }


	    private <T> T convertXmlToObject(String xml, Class<T> typeC) throws JAXBException {
	        Annotation[] annotations = typeC.getAnnotations();

	        StringReader sr = null;
	        for (Annotation annotation : annotations) {
	            if (annotation instanceof XmlRootElement) {
	                XmlRootElement xmlRootElement = (XmlRootElement) annotation;
	                try {
	                    sr = new StringReader(getXmlElement(xml, xmlRootElement.name()));
	                } catch (Exception ex) {
	                    sr = null;
	                }
	                break;
	            }
	        }

	        if (sr != null) {
	            try {
	                JAXBContext jaxbContext = JAXBContext.newInstance(typeC);
	                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	                return (T) jaxbUnmarshaller.unmarshal(sr);
	            } catch (Exception ex) {
	            }
	        }

	        try {
	            return typeC.newInstance();
	        } catch (Exception ex) {
	            return null;
	        }
	    }

	    private String getXmlElement(String xml, String element) {
	        String elementStart = "<" + element;
	        String elementEnd = "</" + element + ">";
	        String elementStartEnd = "<" + element + " />";

	        int inicio = xml.indexOf(elementStart) + elementStart.length();
	        int fim = xml.indexOf(elementEnd);

	        if (xml.indexOf(elementStartEnd) >= 0 || fim < 0) {
	            return "";
	        }

	        return elementStart + xml.substring(inicio, fim) + elementEnd;
	    }

}
