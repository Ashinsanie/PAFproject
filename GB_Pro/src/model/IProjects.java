package model;

import java.util.List;

import com.ProjectsService;

public interface IProjects {
	public int addProject(com.ProjectsService pr);
	public void removeProject(int id);
	public ProjectsService getProById(int id);
	public List<ProjectsService> getAllPro();
	void updateProject(ProjectsService p);

}