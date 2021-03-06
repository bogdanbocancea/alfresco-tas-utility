package org.alfresco.utility.data;

import static org.alfresco.utility.report.log.Step.STEP;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.alfresco.dataprep.CMISUtil.Priority;
import org.alfresco.dataprep.WorkflowService.TaskStatus;
import org.alfresco.dataprep.WorkflowService;
import org.alfresco.utility.model.GroupModel;
import org.alfresco.utility.model.ProcessModel;
import org.alfresco.utility.model.TaskModel;
import org.alfresco.utility.model.UserModel;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Claudia Agache on 10/11/2016.
 */
@Service
@Scope(value = "prototype")
public class DataWorkflow extends TestData<DataWorkflow>
{
    @Autowired
    WorkflowService workflowService;

    /**
     * Example of usage:
     * dataWorkflow.usingUser(userWhoStartsTask).usingSite(siteModel).usingResource(document).createNewTaskAndAssignTo(assignee);
     * 
     * @param userModel
     * @return
     */
    public TaskModel createNewTaskAndAssignTo(UserModel userModel)
    {
        return createNewTask(new TaskModel(userModel.getUsername()));
    }
    
    /**
     * Example of usage:
     * dataWorkflow.usingUser(userWhoStartsTask).usingSite(siteModel).usingResource(document).createNewTaskAndAssignTo(assignee, "@newtenant@activitiAdhoc:1:4888");
     * Note: The process definition id is dynamic in case of an evironment with tenants
     * 
     * @param userModel
     * @param processDefId id of a process definition
     * @return
     */
    public TaskModel createTaskWithProcessDefAndAssignTo(String processDefId, UserModel userModel)
    {
        return createTaskWithProcessDefId(new TaskModel(userModel.getUsername()), processDefId);
    }

    /**
     * Creates a new {@link TaskModel}
     * 
     * @param taskModel
     * @return
     */
    public TaskModel createNewTask(TaskModel taskModel)
    {
        STEP(String.format("DATAPREP: User %s creates new task %s and assigns it to user %s", getCurrentUser().getUsername(), taskModel.getMessage(),
                taskModel.getAssignee()));

        String workflowId = workflowService.startNewTask(getCurrentUser().getUsername(), 
                                                         getCurrentUser().getPassword(), 
                                                         taskModel.getMessage(),
                                                         taskModel.getDueDate(), 
                                                         taskModel.getAssignee(), 
                                                         taskModel.getPriority(), 
                                                         getCurrentSite(),
                                                         Arrays.asList(new File(getLastResource()).getName()), taskModel.getSendEmail());
        taskModel.setProcessId(workflowId);
        if(taskModel.getAssignee().equals(getAdminUser().getUsername()))
        {
            taskModel.setId(workflowService.getTaskId(taskModel.getAssignee(), getAdminUser().getPassword(), workflowId));
        }
        else
        {
            taskModel.setId(workflowService.getTaskId(taskModel.getAssignee(), DataUser.PASSWORD, workflowId));
        }
        return taskModel;
    }
    
    /**
     * Creates a new {@link TaskModel}
     * 
     * @param taskModel
     * @return
     */
    public TaskModel createTaskWithProcessDefId(TaskModel taskModel, String processDefId)
    {
        STEP(String.format("DATAPREP: User %s creates new task %s with and assigns it to user %s", getCurrentUser().getUsername(), taskModel.getMessage(),
                taskModel.getAssignee()));

        String processId = workflowService.startProcessWithDefinitionId(processDefId,
                                                         getCurrentUser().getUsername(), 
                                                         getCurrentUser().getPassword(), 
                                                         taskModel.getMessage(),
                                                         taskModel.getDueDate(), 
                                                         taskModel.getAssignee(), 
                                                         taskModel.getPriority(), 
                                                         getCurrentSite(),
                                                         Arrays.asList(new File(getLastResource()).getName()), taskModel.getSendEmail());
        taskModel.setProcessId(processId);
        if(taskModel.getAssignee().equals(getAdminUser().getUsername()))
        {
            taskModel.setId(workflowService.getTaskId(taskModel.getAssignee(), getAdminUser().getPassword(), processId));
        }
        else
        {
            taskModel.setId(workflowService.getTaskId(taskModel.getAssignee(), getCurrentUser().getPassword(), processId));
        }
        return taskModel;
    }

    /**
     * Starts a Review and Approve(pooled review) workflow with items added from a site
     * Example of usage:
     * dataWorkflow.usingUser(userWhoStartsTask).usingSite(siteModel).usingResource(document).createNewTaskAndAssignTo(assignee);
     * 
     * @param userModel
     * @return
     */
    public TaskModel createPooledReviewTaskAndAssignTo(GroupModel groupModel)
    {
        TaskModel taskModel = new TaskModel(groupModel.getDisplayName());
        STEP(String.format("DATAPREP: User %s creates new task %s and assigns it to group %s", getCurrentUser().getUsername(), taskModel.getMessage(),
                taskModel.getAssignee()));

        String workflowId = workflowService.startPooledReview(getCurrentUser().getUsername(), 
                                                              getCurrentUser().getPassword(), 
                                                              taskModel.getMessage(),
                                                              taskModel.getDueDate(), 
                                                              taskModel.getAssignee(), 
                                                              taskModel.getPriority(), 
                                                              getCurrentSite(),
                                                              Arrays.asList(new File(getLastResource()).getName()), taskModel.getSendEmail());
        taskModel.setProcessId(workflowId);
        taskModel.setId(workflowService.getTaskId(getCurrentUser().getUsername(), getCurrentUser().getPassword(), workflowId));
        return taskModel;
    }

    public TaskModel claimTask(TaskModel taskModel)
    {
        STEP(String.format("DATAPREP: User %s claims task %s", getCurrentUser().getUsername(), taskModel.getMessage()));
        workflowService.claimTask(getCurrentUser().getUsername(), getCurrentUser().getPassword(), taskModel.getProcessId());
        return taskModel;
    }

    /**
     * Starts a Review and Approve(one or more reviewers) workflow with items added from a site
     * Example of usage:
     * dataWorkflow.usingUser(userWhoStartsTask).usingSite(siteModel).usingResource(document).createMoreReviewersWorkflowAndAssignTo(assignees);
     * 
     * @param userModel
     * @return
     */
    public ProcessModel createMoreReviewersWorkflowAndAssignTo(UserModel... users)
    {
        STEP(String.format("DATAPREP: User %s creates 'One or More Reviewers' workflow/process", getCurrentUser().getUsername()));
        ProcessModel process = new ProcessModel();

        List<String> reviewers = new ArrayList<String>();
        for (UserModel user : users)
        {
            reviewers.add(user.getUsername());
        }

        DateTime today = new DateTime();
        String workflowId = workflowService.startMultipleReviewers(getCurrentUser().getUsername(), 
                                                                   getCurrentUser().getPassword(),
                                                                   RandomData.getRandomAlphanumeric(), 
                                                                   today.plusDays(2).toDate(), 
                                                                   reviewers, 
                                                                   Priority.High, 
                                                                   getCurrentSite(),
                                                                   Arrays.asList(new File(getLastResource()).getName()), 0, true);

        process.setId(workflowId);
        for (UserModel user : users)
        {
            TaskModel task = new TaskModel();
            task.setId(workflowService.getTaskId(user.getUsername(), user.getPassword(), workflowId));
            task.setAssignee(user.getUsername());
            process.addTaskToList(task);
        }
        return process;
    }
    
    /**
     * Starts a Review and Approve (group review) workflow with items added from a site
     * Example of usage:
     * dataWorkflow.usingUser(userWhoStartsTask).usingSite(siteModel).usingResource(document).createNewTaskAndAssignTo(assignee);
     * 
     * @param userModel
     * @return
     */
    public ProcessModel createGroupReviewTaskAndAssignTo(GroupModel groupModel)
    {
        ProcessModel process = new ProcessModel();
        STEP(String.format("DATAPREP: User %s creates new 'group review' workflow and assigns it to group %s", getCurrentUser().getUsername(), 
                groupModel.getDisplayName()));

        DateTime today = new DateTime();
        String workflowId = workflowService.startGroupReview(getCurrentUser().getUsername(), 
                                                              getCurrentUser().getPassword(), 
                                                              RandomData.getRandomAlphanumeric(),
                                                              today.plusDays(2).toDate(), 
                                                              groupModel.getDisplayName(), 
                                                              Priority.High, 
                                                              getCurrentSite(),
                                                              Arrays.asList(new File(getLastResource()).getName()), 0, true);
        
        process.setId(workflowId);
        return process;
    }
    
    /**
     * Starts a Review and Approve (single reviewer) workflow with items added from a site
     * Example of usage:
     * dataWorkflow.usingUser(userWhoStartsTask).usingSite(siteModel).usingResource(document).createNewTaskAndAssignTo(assignee);
     * 
     * @param userModel
     * @return
     */
    public ProcessModel createSingleReviewerTaskAndAssignTo(UserModel userModel)
    {
        ProcessModel process = new ProcessModel();
        STEP(String.format("DATAPREP: User %s creates new 'single reviewer' workflow and assigns it to user %s", getCurrentUser().getUsername(), 
                userModel.getUsername()));

        DateTime today = new DateTime();
        String workflowId = workflowService.startSingleReview(getCurrentUser().getUsername(), 
                                                              getCurrentUser().getPassword(), 
                                                              RandomData.getRandomAlphanumeric(),
                                                              today.plusDays(2).toDate(), 
                                                              userModel.getUsername(), 
                                                              Priority.High, 
                                                              getCurrentSite(),
                                                              Arrays.asList(new File(getLastResource()).getName()), true);
        
        process.setId(workflowId);
        return process;
    }

    public ProcessModel approveTask(ProcessModel processModel)
    {
        STEP(String.format("DATAPREP: User %s approves task %s", getCurrentUser().getUsername(), processModel.getId()));

        workflowService.approveTask(getCurrentUser().getUsername(), getCurrentUser().getPassword(), processModel.getId(), true, TaskStatus.COMPLETED, "");
        return processModel;
    }
    
    /**
     * Mark a task as done
     * @param taskModel {@link TaskModel}
     */
    public TaskModel taskDone(TaskModel taskModel)
    {
        STEP(String.format("DATAPREP: User %s completes task %s", getCurrentUser().getUsername(), taskModel.getId()));
        workflowService.taskDone(getCurrentUser().getUsername(), getCurrentUser().getPassword(), taskModel.getProcessId(), TaskStatus.COMPLETED, "complete task");
        return taskModel;
    }
    
    /**
     * Delete process
     * @param processModel {@link ProcessModel}
     * @return
     */
    public boolean deleteProcess(ProcessModel processModel)
    {
        STEP(String.format("DATAPREP: User %s - delete process %s", getCurrentUser().getUsername(), processModel.getId()));
        return workflowService.deleteWorkflow(getCurrentUser().getUsername(), getCurrentUser().getPassword(), processModel.getId());
    }

    /**
     * Cancel process
     * @param processModel {@link ProcessModel}
     * @return
     */
    public boolean cancelProcess(ProcessModel processModel)
    {
        STEP(String.format("DATAPREP: User %s - cancel process %s", getCurrentUser().getUsername(), processModel.getId()));
        return workflowService.cancelWorkflow(getCurrentUser().getUsername(), getCurrentUser().getPassword(), processModel.getId());
    }
}
