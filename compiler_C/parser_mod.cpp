#include <iostream>
using namespace std;
#include "lexical_mod.cpp"

// Program  -> main () {  declarations   statement-list }
// declarations-> data-type identifier-list; declarations | epsilon
// data-type -> int|char
// identifier-list -> id|id, identifier-list
// statement_list -> statement statement_list | epsilon
// statement -> assign-stat;
// assign_stat -> id = expn

void assignstat();
void Program();
void declarations();
void identifierlist();
void statementlist();
void statement();
void expn();
void eprime();
void simplexp();
void seprime();
void term();
void tprime();
void factor();
void decisionstat();
void dprime();
void relop();
void addop();
void mulop();
void loopingstat();
int flag=0;
char str1[20];

void error()
{
   cout<<"Error,sorry the code which I read is not a valid C code\n";
   flag=1;
   exit(1);
}

void eprime()
{
	strcpy(str,get());
	j=0;
	if(str[j]=='=' || str[j]=='>' || str[j]=='<' || str[j]=='!' )
	{
	relop();
	simplexp();
	}
	else
	return ;
}

void term()
{
	factor();
	tprime();
}

void seprime()
{
	if(strcmp(mat[kx+1],"+")==0 || strcmp(mat[kx+1],"-")==0)
	{
	strcpy(str,get());
	addop();
	term();
	seprime();
	}
	else
	return;
}

void decisionstat()
{
	if(!strcmp(str,"if"))
	{
		strcpy(str,get());
		j=0;
				if(!strcmp(str,"("))
				{
				//	cout<<"(:"<<str<<endl;
					expn();
				}
				else
				error();
				strcpy(str,get());
				j=0;
				if(!strcmp(str,")"))
				{
				//	cout<<"):"<<str<<endl;
				}
				else
				error();
				strcpy(str,get());
				j=0;
				if(strcmp(str,"{")==0)
				{
					 strcpy(str,get());
					 j=0;
				//	 cout<<"string before stat list\t"<<str<<endl;
					 statementlist();
				}
				else
				{
					cout<<"There is a missing {\n";
					error();
				}
				strcpy(str,get());
				j=0;
				if(strcmp(str,"}")==0)
				{
					cout<<"There is a missing }\n";
					error();
				}
	}
	else
		error();
}

void dprime()
{
	strcpy(str,get());
	j=0;
	if(!strcmp(str,"else"))
	{
		cout<<"";
	}
}

void tprime()
{
	if(strcmp(mat[kx+1],"*")==0 || strcmp(mat[kx+1],"/")==0 || strcmp(mat[kx+1],"%")==0)
	{
	strcpy(str,get());
	mulop();
	factor();
	tprime();
	}
	else
	return ;
}

void factor()
{
strcpy(str,get());
	j=0;
//	cout<<"factor: "<<str<<endl;
	if(isalnum(str[j]) || str[j]=='_')
	{
	//	strcpy(str,get());
	//	j=0;
	//	cout<<"inside factor\t"<<str<<endl;
	}
	else
	{
		error();
	}
}

void relop()
{

	if(str[j]=='=' && str[j+1]=='=')
	{
	//strcpy(str,get());
	//j=0;

	}
	else if(str[j]=='!' && str[j+1]=='=')
{
//strcpy(str,get());
	//j=0;

}
	else if(str[j]=='<' && str[j+1]=='=')
	{
//strcpy(str,get());
	//j=0;

}
	else if(str[j]=='>' && str[j+1]=='=')
	{
//strcpy(str,get());
	//j=0;
}
	else if(str[j]=='>')
	{
//strcpy(str,get());
	//j=0;

}
	else if(str[j]=='<')
	{
//strcpy(str,get());
	//j=0;

}
	else
	{
			error();
	}

	//return ;
}


void addop()
{
	if(str[j]=='+')
	j=j+1;
	else if(str[j]=='-')
	j=j+1;
	else 
	{
		cout<<"Expected + or -\n";
		error();
	}
}

void mulop()
{
//	cout<<"mulop:\t"<<str<<endl;
	if(str[j]=='*')
	j=j+1;
	else if(str[j]=='/')
	j=j+1;
	else if(str[j]=='%')
	j=j+1;
	else error();
}


void Program()
{

	if (strcmp(str, "int")==0)
	{
		strcpy(str, get());
		if(strcmp(str,"main")==0)
		{
			strcpy(str,get());

			j=0;
		 	if(str[j]=='(')
			{
			strcpy(str,get());
			j=0;
			}
			else
			error();
			if(str[j]==')')
			{
				strcpy(str,get());
				j=0;
			}
			else error();

			if(str[j]=='{')
			{
				declarations();
				statementlist();
				if(str[j]=='}')
				{

					strcpy(str,get());
					j=0;
				if(!strcmp(str,"$"))
				{
					cout<<"error free!!!\n";
					exit(0);
				}
				}
				else
				error();
			}
			else
			{
				cout<<"There is a missing {\n";
				error();
			}
		}
		else
		error();
	}
	else
	{
		cout<<"missing \"int\" before \"main\"\n";
		error();
	}
		return ;
}

void declarations()
{
		strcpy(str,get());
		j=0;

		if(strcmp(str,"int")==0 || strcmp(str,"char")==0)
		{

			identifierlist();
			if(!strcmp(str,"$"))
			{
				cout<<"error_free!!!\n";
				exit(0);
			}
			if(str[j]==';')
			{
				declarations();
			}
			else
			error();
		}
		else if(str[j]==';')
			{
				declarations();
			}
			else
		return ;
}


void identifierlist()
{
	strcpy(str,get());
	j=0;
	if(isalpha(str[j]) || str[j]=='_')
	{

		while(isalnum(str[j]) || str[j]=='_')
		{
		//cout<<"i;;"<<str<<endl;
		j++;
		}
		strcpy(str,get());
		j=0;

		if(str[j]==',')
		{
			identifierlist();
		}
		else if(str[j]=='[')
		{

			strcpy(str,get());
			j=0;

			if(isdigit(str[j]))
			{


				strcpy(str,get());
				j=0;
				if(str[j]==']')
				{

				strcpy(str,get());
					j=0;
					if(str[j]==';')
					{
					strcpy(str,get());
					j=0;
					if(str[j]=='}')
					{
						strcpy(str,get());
						j=0;
						if(str[j]=='$')
						{
						cout<<"error free !!!"<<endl;
						exit(0);
						}
					}
					else
					error();
					}
					else
					error();
					if(str[j]==',')
			{
				identifierlist();
			}
				}
				else
				error();
			}
			else error();
		}
	}
	else
	return ;
}


void  statementlist()
{

	if(isalpha(str[j]) || str[j]=='_')
	{
	statement();
	statementlist();
	}
	else
	return ;
}


void assignstat()
{
	 strcpy(str,get());
	 j=0;
	 //cout<<"assign "<<str<<endl;
	if(str[j]=='=')
		{
	//	cout<<"assignstat:\t"<<str<<endl;
	//	cout<<str<<endl;
		expn();
		}
		else
		error();
}

void statement()
{
		//cout<<"state"<<str<<endl;
		//cout<<"statement\t\t"<<str<<endl;
		 if(strcmp(mat[kx],"if")==0)
	{
		decisionstat();
	}
	else if(strcmp(mat[kx],"while")==0 || strcmp(mat[kx],"for")==0)
	{

		loopingstat();
	}
	else if(isalpha(str[j]) || str[j]=='_')
	{
		/*strcpy(str,get());
		j=0;
		cout<<"state"<<str<<endl;*/
		assignstat();
		//cout<<"check this :"<<str<<endl;
//		cout<<"after assign stat: "<<str<<endl;
			if(isalnum(str[j]))
			{
			strcpy(str,get());
			j=0;
			}
		if(str[j]==';')
		{
		strcpy(str,get());
		j=0;

		}
		else
		error();
	}

	else
	error();
	return ;
}

void loopingstat()
{
		if(strcmp(str,"while")==0)
		{
			strcpy(str,get());
			j=0;
			if(strcmp(str,"(")==0)
			{
				expn();
				strcpy(str,get());
				j=0;
				if(strcmp(str,")")==0)
				{
					strcpy(str,get());
					j=0;

						if(strcmp(str,"{")==0)
						{
						 strcpy(str,get());
					 		j=0;
							statementlist();
						 strcpy(str,get());
					 		j=0;
								if(strcmp(str,"}")==0)
								{

								}
								else
								{
									cout<<"There is a missing }\n";
									error();
								}
						}
						else
						{
							cout<<"There is a missing {\n";
							error();
						}
				}
				else
				{
				cout<<") missing\n";
				error();
				}
				}
			else
			{
				cout<<"( missing\n";
				error();
			}
		}
		else if(strcmp(str,"for")==0)
		{
			strcpy(str,get());
			j=0;
	
			if(strcmp(str,"(")==0)
			{
			//	cout<<str<<endl;
				strcpy(str,get());
				j=0;
				assignstat();
			//	cout<<str<<endl;
			if(strcmp(str,";")==0)
			{	
				expn();
			//	cout<<str<<endl;
				strcpy(str,get());
				j=0;
			}
			else
			{
				cout<<"There is a missing semicolon\n";
				error();
			}

			if(strcmp(str,";")==0)
			{
				strcpy(str,get());
				j=0;
				assignstat();
				strcpy(str,get());
				j=0;
			}
			else
			{
				cout<<"There is a missing semicolon\n";
				error();
			}
		
			if(strcmp(str,")"))
			{
			
			}
			else
			error();
			
			if(!strcmp(str,"{"))
			{
					 	    strcpy(str,get());
					 		j=0;
							statementlist();
							 strcpy(str,get());
					 		j=0;
					 		if(!strcmp(str,"}"))
					 		{
							
					 		}
					 		else
					 		error();
			}
			else
			error();
			}
			else
			error();
		}
		else
		error();
}

void expn()
{
		simplexp();
		eprime();
}

void simplexp()
{
	term();
	seprime();
}

int main()
{
readf();
display();
strcpy(str,get());
j=0;
Program();
return 0;
}

